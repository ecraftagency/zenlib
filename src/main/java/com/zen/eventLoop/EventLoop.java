package com.zen.eventLoop;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*simple event loop implement in java[/-script-/]*/
@SuppressWarnings("unused")
public class EventLoop implements Runnable {
  static final int NUM_CORE = 4;
  static final int NUM_THREAD_PER_POOL = NUM_CORE / 2;
  public static ScheduledThreadPoolExecutor schThreadPool = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(NUM_THREAD_PER_POOL);

  private ConcurrentLinkedQueue<Command> incomingCommands;

  public EventLoop() {
    incomingCommands = new ConcurrentLinkedQueue<>();
  }

  @Override
  public void run() {
    while (!incomingCommands.isEmpty()) {
      try {
        Command command = incomingCommands.poll();
        if (command != null)
          command.execute();
      }
      catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    schThreadPool.schedule(this, 1, TimeUnit.MILLISECONDS);
  }

  @FunctionalInterface
  public interface Command {
    void execute();
  }

  public void addCommand(Command command) {
    incomingCommands.add(command);
  }
}