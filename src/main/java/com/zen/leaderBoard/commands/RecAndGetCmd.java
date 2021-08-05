package com.zen.leaderBoard.commands;

import com.zen.eventLoop.EventLoop;
import com.zen.eventLoop.hasKey;
import com.zen.leaderBoard.LeaderBoard;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

import java.util.List;

@SuppressWarnings("unused")
public class RecAndGetCmd<K, V extends Comparable<V> & hasKey<K>> implements EventLoop.Command {
  LeaderBoard<K, V>             ldb;
  V                             so;
  Handler<AsyncResult<List<V>>> handler;

  public RecAndGetCmd(LeaderBoard<K, V> ldb, V so, Handler<AsyncResult<List<V>>> handler) {
    this.ldb = ldb;
    this.so  = so;
    this.handler = handler;
  }

  @Override
  public void execute() {
    ldb.record(so.mapKey(), so);
    handler.handle(Future.succeededFuture(ldb.get()));
  }
}