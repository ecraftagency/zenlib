package com.zen.leaderBoard;

import com.zen.eventLoop.hasKey;

public class ScoreObj implements Comparable<ScoreObj>, hasKey<Long> {
  public long     id;
  public long     score;
  public String   name;

  public static ScoreObj of(long id, long score, String name) {
    ScoreObj scoreObj = new ScoreObj();
    scoreObj.id = id;
    scoreObj.score = score;
    scoreObj.name = name;
    return scoreObj;
  }

  @Override
  public int compareTo(ScoreObj o) {
    if (score > o.score)
      return 1;
    else if (score < o.score)
      return -1;
    return 0;
  }

  @Override
  public Long mapKey() {
    return id;
  }
}