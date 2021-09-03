package jcip.ex09;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.*;

/**
 * <h6>CodeList 9-4|9-5|9-6|9-8 ListenerExamples</h6>
 * <i>Binding a long-running task to a visual component.</i><br>
 * <i>Long-running task with user feedback.</i><br>
 * <i>Cancelling a long-running task.</i><br>
 * <i>Initiating a long-running, cancellable task with BackgroundTask.</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class ListenerExamples {
	
	private static ExecutorService exec = Executors.newCachedThreadPool();

	private final JButton colorButton = new JButton("Change color");
	private final Random random = new Random();

	private void backgroundRandom() {
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorButton.setBackground(new Color(random.nextInt()));
			}
		});
	}

	private final JButton computeButton = new JButton("Big computation");

	private void longRunningTask() {
		computeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exec.execute(new Runnable() {
					public void run() {
						/* Do big computation */
					}
				});
			}
		});
	}

	private final JButton button = new JButton("Do");
	private final JLabel label = new JLabel("idle");

	private void longRunningTaskWithFeedback() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setEnabled(false);
				label.setText("busy");
				exec.execute(new Runnable() {
					public void run() {
						try {
							/* Do big computation */
						} finally {
							GuiExecutor.instance().execute(new Runnable() {
								public void run() {
									button.setEnabled(true);
									label.setText("idle");
								}
							});
						}
					}
				});
			}
		});
	}

	private final JButton startButton = new JButton("Start");
	private final JButton cancelButton = new JButton("Cancel");
	private Future<?> runningTask = null; // thread-confined

	private void taskWithCancellation() {
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (runningTask != null) {
					runningTask = exec.submit(new Runnable() {
						public void run() {
							while (moreWork()) {
								if (Thread.currentThread().isInterrupted()) {
									cleanUpPartialWork();
									break;
								}
								doSomeWork();
							}
						}

						private boolean moreWork() {
							return false;
						}

						private void cleanUpPartialWork() {
						}

						private void doSomeWork() {
						}

					});
				}
				;
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (runningTask != null)
					runningTask.cancel(true);
			}
		});
	}

	private void runInBackground(final Runnable task) {
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				class CancelListener implements ActionListener {
					BackgroundTask<?> task;

					public void actionPerformed(ActionEvent event) {
						if (task != null)
							task.cancel(true);
					}
				}
				final CancelListener listener = new CancelListener();
				listener.task = new BackgroundTask<Void>() {
					public Void compute() {
						while (moreWork() && !isCancelled())
							doSomeWork();
						return null;
					}

					private boolean moreWork() {
						return false;
					}

					private void doSomeWork() {
					}

					public void onCompletion(boolean cancelled, String s,
							Throwable exception) {
						cancelButton.removeActionListener(listener);
						label.setText("done");
					}
				};
				cancelButton.addActionListener(listener);
				exec.execute(task);
			}
		});
	}
}
