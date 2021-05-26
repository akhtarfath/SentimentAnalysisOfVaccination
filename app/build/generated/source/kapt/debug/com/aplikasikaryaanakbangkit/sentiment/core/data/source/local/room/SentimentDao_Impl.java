package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleCovidEntity;
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleVaccinesEntity;
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.DataItemTweetEntity;
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.PublicMetricsTweetEntity;
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TeamsEntity;
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TweetEntity;
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.UserItemsTweetEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SentimentDao_Impl implements SentimentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ArticleCovidEntity> __insertionAdapterOfArticleCovidEntity;

  private final EntityInsertionAdapter<ArticleVaccinesEntity> __insertionAdapterOfArticleVaccinesEntity;

  private final EntityInsertionAdapter<TeamsEntity> __insertionAdapterOfTeamsEntity;

  private final EntityInsertionAdapter<DataItemTweetEntity> __insertionAdapterOfDataItemTweetEntity;

  private final EntityInsertionAdapter<UserItemsTweetEntity> __insertionAdapterOfUserItemsTweetEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdatePostByMetrics;

  public SentimentDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArticleCovidEntity = new EntityInsertionAdapter<ArticleCovidEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `articleCovid` (`url`,`author`,`urlToImage`,`description`,`title`,`publishedAt`,`content`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ArticleCovidEntity value) {
        if (value.getUrl() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUrl());
        }
        if (value.getAuthor() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAuthor());
        }
        if (value.getUrlToImage() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrlToImage());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getTitle());
        }
        if (value.getPublishedAt() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPublishedAt());
        }
        if (value.getContent() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getContent());
        }
      }
    };
    this.__insertionAdapterOfArticleVaccinesEntity = new EntityInsertionAdapter<ArticleVaccinesEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `articleVaccine` (`url`,`author`,`urlToImage`,`description`,`title`,`publishedAt`,`content`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ArticleVaccinesEntity value) {
        if (value.getUrl() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUrl());
        }
        if (value.getAuthor() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAuthor());
        }
        if (value.getUrlToImage() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrlToImage());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getTitle());
        }
        if (value.getPublishedAt() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPublishedAt());
        }
        if (value.getContent() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getContent());
        }
      }
    };
    this.__insertionAdapterOfTeamsEntity = new EntityInsertionAdapter<TeamsEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `teams` (`id`,`name`,`urlPicture`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TeamsEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getUrlPicture() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrlPicture());
        }
      }
    };
    this.__insertionAdapterOfDataItemTweetEntity = new EntityInsertionAdapter<DataItemTweetEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `tweetPost` (`id`,`created_at`,`text`,`authorId`,`likeCount`,`replyCount`,`quoteCount`,`retweetCount`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DataItemTweetEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getCreatedAt() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCreatedAt());
        }
        if (value.getText() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getText());
        }
        if (value.getAuthorId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAuthorId());
        }
        final PublicMetricsTweetEntity _tmpPublicMetrics = value.getPublicMetrics();
        if(_tmpPublicMetrics != null) {
          if (_tmpPublicMetrics.getLikeCount() == null) {
            stmt.bindNull(5);
          } else {
            stmt.bindLong(5, _tmpPublicMetrics.getLikeCount());
          }
          if (_tmpPublicMetrics.getReplyCount() == null) {
            stmt.bindNull(6);
          } else {
            stmt.bindLong(6, _tmpPublicMetrics.getReplyCount());
          }
          if (_tmpPublicMetrics.getQuoteCount() == null) {
            stmt.bindNull(7);
          } else {
            stmt.bindLong(7, _tmpPublicMetrics.getQuoteCount());
          }
          if (_tmpPublicMetrics.getRetweetCount() == null) {
            stmt.bindNull(8);
          } else {
            stmt.bindLong(8, _tmpPublicMetrics.getRetweetCount());
          }
        } else {
          stmt.bindNull(5);
          stmt.bindNull(6);
          stmt.bindNull(7);
          stmt.bindNull(8);
        }
      }
    };
    this.__insertionAdapterOfUserItemsTweetEntity = new EntityInsertionAdapter<UserItemsTweetEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `tweetProfile` (`authorId`,`name`,`profile_image_url`,`username`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserItemsTweetEntity value) {
        if (value.getAuthorId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getAuthorId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getProfileImageUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getProfileImageUrl());
        }
        if (value.getUsername() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUsername());
        }
      }
    };
    this.__preparedStmtOfUpdatePostByMetrics = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE tweetPost SET likeCount = ?, replyCount = ?, quoteCount = ?, retweetCount = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertCovidArticles(final List<ArticleCovidEntity> article) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfArticleCovidEntity.insert(article);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertVaccineArticles(final List<ArticleVaccinesEntity> article) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfArticleVaccinesEntity.insert(article);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertTeams(final List<TeamsEntity> teams) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTeamsEntity.insert(teams);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertTweets(final List<DataItemTweetEntity> tweet) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDataItemTweetEntity.insert(tweet);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertTweetProfile(final List<UserItemsTweetEntity> profile) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserItemsTweetEntity.insert(profile);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updatePostByMetrics(final int likeCount, final int replyCount, final int quoteCount,
      final int retweetCount, final String id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdatePostByMetrics.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, likeCount);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, replyCount);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, quoteCount);
    _argIndex = 4;
    _stmt.bindLong(_argIndex, retweetCount);
    _argIndex = 5;
    if (id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, id);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdatePostByMetrics.release(_stmt);
    }
  }

  @Override
  public DataSource.Factory<Integer, ArticleCovidEntity> getCovidArticles() {
    final String _sql = "SELECT * FROM articleCovid ORDER BY publishedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new DataSource.Factory<Integer, ArticleCovidEntity>() {
      @Override
      public LimitOffsetDataSource<ArticleCovidEntity> create() {
        return new LimitOffsetDataSource<ArticleCovidEntity>(__db, _statement, false, true , "articleCovid") {
          @Override
          protected List<ArticleCovidEntity> convertRows(Cursor cursor) {
            final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(cursor, "url");
            final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(cursor, "author");
            final int _cursorIndexOfUrlToImage = CursorUtil.getColumnIndexOrThrow(cursor, "urlToImage");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(cursor, "description");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "title");
            final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(cursor, "publishedAt");
            final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(cursor, "content");
            final List<ArticleCovidEntity> _res = new ArrayList<ArticleCovidEntity>(cursor.getCount());
            while(cursor.moveToNext()) {
              final ArticleCovidEntity _item;
              final String _tmpUrl;
              if (cursor.isNull(_cursorIndexOfUrl)) {
                _tmpUrl = null;
              } else {
                _tmpUrl = cursor.getString(_cursorIndexOfUrl);
              }
              final String _tmpAuthor;
              if (cursor.isNull(_cursorIndexOfAuthor)) {
                _tmpAuthor = null;
              } else {
                _tmpAuthor = cursor.getString(_cursorIndexOfAuthor);
              }
              final String _tmpUrlToImage;
              if (cursor.isNull(_cursorIndexOfUrlToImage)) {
                _tmpUrlToImage = null;
              } else {
                _tmpUrlToImage = cursor.getString(_cursorIndexOfUrlToImage);
              }
              final String _tmpDescription;
              if (cursor.isNull(_cursorIndexOfDescription)) {
                _tmpDescription = null;
              } else {
                _tmpDescription = cursor.getString(_cursorIndexOfDescription);
              }
              final String _tmpTitle;
              if (cursor.isNull(_cursorIndexOfTitle)) {
                _tmpTitle = null;
              } else {
                _tmpTitle = cursor.getString(_cursorIndexOfTitle);
              }
              final String _tmpPublishedAt;
              if (cursor.isNull(_cursorIndexOfPublishedAt)) {
                _tmpPublishedAt = null;
              } else {
                _tmpPublishedAt = cursor.getString(_cursorIndexOfPublishedAt);
              }
              final String _tmpContent;
              if (cursor.isNull(_cursorIndexOfContent)) {
                _tmpContent = null;
              } else {
                _tmpContent = cursor.getString(_cursorIndexOfContent);
              }
              _item = new ArticleCovidEntity(_tmpUrl,_tmpAuthor,_tmpUrlToImage,_tmpDescription,_tmpTitle,_tmpPublishedAt,_tmpContent);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  @Override
  public LiveData<ArticleCovidEntity> getCovidArticleByUrl(final String url) {
    final String _sql = "SELECT * FROM articleCovid WHERE url = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (url == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, url);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"articleCovid"}, false, new Callable<ArticleCovidEntity>() {
      @Override
      public ArticleCovidEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfUrlToImage = CursorUtil.getColumnIndexOrThrow(_cursor, "urlToImage");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final ArticleCovidEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpAuthor;
            if (_cursor.isNull(_cursorIndexOfAuthor)) {
              _tmpAuthor = null;
            } else {
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            }
            final String _tmpUrlToImage;
            if (_cursor.isNull(_cursorIndexOfUrlToImage)) {
              _tmpUrlToImage = null;
            } else {
              _tmpUrlToImage = _cursor.getString(_cursorIndexOfUrlToImage);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpPublishedAt;
            if (_cursor.isNull(_cursorIndexOfPublishedAt)) {
              _tmpPublishedAt = null;
            } else {
              _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            _result = new ArticleCovidEntity(_tmpUrl,_tmpAuthor,_tmpUrlToImage,_tmpDescription,_tmpTitle,_tmpPublishedAt,_tmpContent);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public DataSource.Factory<Integer, ArticleVaccinesEntity> getVaccineArticles() {
    final String _sql = "SELECT * FROM articleVaccine ORDER BY publishedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new DataSource.Factory<Integer, ArticleVaccinesEntity>() {
      @Override
      public LimitOffsetDataSource<ArticleVaccinesEntity> create() {
        return new LimitOffsetDataSource<ArticleVaccinesEntity>(__db, _statement, false, true , "articleVaccine") {
          @Override
          protected List<ArticleVaccinesEntity> convertRows(Cursor cursor) {
            final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(cursor, "url");
            final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(cursor, "author");
            final int _cursorIndexOfUrlToImage = CursorUtil.getColumnIndexOrThrow(cursor, "urlToImage");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(cursor, "description");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "title");
            final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(cursor, "publishedAt");
            final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(cursor, "content");
            final List<ArticleVaccinesEntity> _res = new ArrayList<ArticleVaccinesEntity>(cursor.getCount());
            while(cursor.moveToNext()) {
              final ArticleVaccinesEntity _item;
              final String _tmpUrl;
              if (cursor.isNull(_cursorIndexOfUrl)) {
                _tmpUrl = null;
              } else {
                _tmpUrl = cursor.getString(_cursorIndexOfUrl);
              }
              final String _tmpAuthor;
              if (cursor.isNull(_cursorIndexOfAuthor)) {
                _tmpAuthor = null;
              } else {
                _tmpAuthor = cursor.getString(_cursorIndexOfAuthor);
              }
              final String _tmpUrlToImage;
              if (cursor.isNull(_cursorIndexOfUrlToImage)) {
                _tmpUrlToImage = null;
              } else {
                _tmpUrlToImage = cursor.getString(_cursorIndexOfUrlToImage);
              }
              final String _tmpDescription;
              if (cursor.isNull(_cursorIndexOfDescription)) {
                _tmpDescription = null;
              } else {
                _tmpDescription = cursor.getString(_cursorIndexOfDescription);
              }
              final String _tmpTitle;
              if (cursor.isNull(_cursorIndexOfTitle)) {
                _tmpTitle = null;
              } else {
                _tmpTitle = cursor.getString(_cursorIndexOfTitle);
              }
              final String _tmpPublishedAt;
              if (cursor.isNull(_cursorIndexOfPublishedAt)) {
                _tmpPublishedAt = null;
              } else {
                _tmpPublishedAt = cursor.getString(_cursorIndexOfPublishedAt);
              }
              final String _tmpContent;
              if (cursor.isNull(_cursorIndexOfContent)) {
                _tmpContent = null;
              } else {
                _tmpContent = cursor.getString(_cursorIndexOfContent);
              }
              _item = new ArticleVaccinesEntity(_tmpUrl,_tmpAuthor,_tmpUrlToImage,_tmpDescription,_tmpTitle,_tmpPublishedAt,_tmpContent);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  @Override
  public LiveData<ArticleVaccinesEntity> getVaccineArticleByUrl(final String url) {
    final String _sql = "SELECT * FROM articleVaccine WHERE url = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (url == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, url);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"articleVaccine"}, false, new Callable<ArticleVaccinesEntity>() {
      @Override
      public ArticleVaccinesEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfUrlToImage = CursorUtil.getColumnIndexOrThrow(_cursor, "urlToImage");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final ArticleVaccinesEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpAuthor;
            if (_cursor.isNull(_cursorIndexOfAuthor)) {
              _tmpAuthor = null;
            } else {
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            }
            final String _tmpUrlToImage;
            if (_cursor.isNull(_cursorIndexOfUrlToImage)) {
              _tmpUrlToImage = null;
            } else {
              _tmpUrlToImage = _cursor.getString(_cursorIndexOfUrlToImage);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpPublishedAt;
            if (_cursor.isNull(_cursorIndexOfPublishedAt)) {
              _tmpPublishedAt = null;
            } else {
              _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            _result = new ArticleVaccinesEntity(_tmpUrl,_tmpAuthor,_tmpUrlToImage,_tmpDescription,_tmpTitle,_tmpPublishedAt,_tmpContent);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public DataSource.Factory<Integer, TeamsEntity> getAllTeams() {
    final String _sql = "SELECT * FROM teams";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new DataSource.Factory<Integer, TeamsEntity>() {
      @Override
      public LimitOffsetDataSource<TeamsEntity> create() {
        return new LimitOffsetDataSource<TeamsEntity>(__db, _statement, false, true , "teams") {
          @Override
          protected List<TeamsEntity> convertRows(Cursor cursor) {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(cursor, "name");
            final int _cursorIndexOfUrlPicture = CursorUtil.getColumnIndexOrThrow(cursor, "urlPicture");
            final List<TeamsEntity> _res = new ArrayList<TeamsEntity>(cursor.getCount());
            while(cursor.moveToNext()) {
              final TeamsEntity _item;
              final String _tmpId;
              if (cursor.isNull(_cursorIndexOfId)) {
                _tmpId = null;
              } else {
                _tmpId = cursor.getString(_cursorIndexOfId);
              }
              final String _tmpName;
              if (cursor.isNull(_cursorIndexOfName)) {
                _tmpName = null;
              } else {
                _tmpName = cursor.getString(_cursorIndexOfName);
              }
              final String _tmpUrlPicture;
              if (cursor.isNull(_cursorIndexOfUrlPicture)) {
                _tmpUrlPicture = null;
              } else {
                _tmpUrlPicture = cursor.getString(_cursorIndexOfUrlPicture);
              }
              _item = new TeamsEntity(_tmpId,_tmpName,_tmpUrlPicture);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  @Override
  public LiveData<List<DataItemTweetEntity>> getAllTweet() {
    final String _sql = "SELECT * FROM tweetPost";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"tweetPost"}, false, new Callable<List<DataItemTweetEntity>>() {
      @Override
      public List<DataItemTweetEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(_cursor, "authorId");
          final int _cursorIndexOfLikeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "likeCount");
          final int _cursorIndexOfReplyCount = CursorUtil.getColumnIndexOrThrow(_cursor, "replyCount");
          final int _cursorIndexOfQuoteCount = CursorUtil.getColumnIndexOrThrow(_cursor, "quoteCount");
          final int _cursorIndexOfRetweetCount = CursorUtil.getColumnIndexOrThrow(_cursor, "retweetCount");
          final List<DataItemTweetEntity> _result = new ArrayList<DataItemTweetEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DataItemTweetEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpAuthorId;
            if (_cursor.isNull(_cursorIndexOfAuthorId)) {
              _tmpAuthorId = null;
            } else {
              _tmpAuthorId = _cursor.getString(_cursorIndexOfAuthorId);
            }
            final PublicMetricsTweetEntity _tmpPublicMetrics;
            if (! (_cursor.isNull(_cursorIndexOfLikeCount) && _cursor.isNull(_cursorIndexOfReplyCount) && _cursor.isNull(_cursorIndexOfQuoteCount) && _cursor.isNull(_cursorIndexOfRetweetCount))) {
              final Integer _tmpLikeCount;
              if (_cursor.isNull(_cursorIndexOfLikeCount)) {
                _tmpLikeCount = null;
              } else {
                _tmpLikeCount = _cursor.getInt(_cursorIndexOfLikeCount);
              }
              final Integer _tmpReplyCount;
              if (_cursor.isNull(_cursorIndexOfReplyCount)) {
                _tmpReplyCount = null;
              } else {
                _tmpReplyCount = _cursor.getInt(_cursorIndexOfReplyCount);
              }
              final Integer _tmpQuoteCount;
              if (_cursor.isNull(_cursorIndexOfQuoteCount)) {
                _tmpQuoteCount = null;
              } else {
                _tmpQuoteCount = _cursor.getInt(_cursorIndexOfQuoteCount);
              }
              final Integer _tmpRetweetCount;
              if (_cursor.isNull(_cursorIndexOfRetweetCount)) {
                _tmpRetweetCount = null;
              } else {
                _tmpRetweetCount = _cursor.getInt(_cursorIndexOfRetweetCount);
              }
              _tmpPublicMetrics = new PublicMetricsTweetEntity(_tmpLikeCount,_tmpReplyCount,_tmpQuoteCount,_tmpRetweetCount);
            }  else  {
              _tmpPublicMetrics = null;
            }
            _item = new DataItemTweetEntity(_tmpId,_tmpCreatedAt,_tmpText,_tmpAuthorId);
            _item.setPublicMetrics(_tmpPublicMetrics);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<DataItemTweetEntity>> getTweetWithProfile(final String authorId) {
    final String _sql = "SELECT * FROM tweetPost WHERE authorId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (authorId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, authorId);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"tweetPost"}, false, new Callable<List<DataItemTweetEntity>>() {
      @Override
      public List<DataItemTweetEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(_cursor, "authorId");
          final int _cursorIndexOfLikeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "likeCount");
          final int _cursorIndexOfReplyCount = CursorUtil.getColumnIndexOrThrow(_cursor, "replyCount");
          final int _cursorIndexOfQuoteCount = CursorUtil.getColumnIndexOrThrow(_cursor, "quoteCount");
          final int _cursorIndexOfRetweetCount = CursorUtil.getColumnIndexOrThrow(_cursor, "retweetCount");
          final List<DataItemTweetEntity> _result = new ArrayList<DataItemTweetEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DataItemTweetEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpAuthorId;
            if (_cursor.isNull(_cursorIndexOfAuthorId)) {
              _tmpAuthorId = null;
            } else {
              _tmpAuthorId = _cursor.getString(_cursorIndexOfAuthorId);
            }
            final PublicMetricsTweetEntity _tmpPublicMetrics;
            if (! (_cursor.isNull(_cursorIndexOfLikeCount) && _cursor.isNull(_cursorIndexOfReplyCount) && _cursor.isNull(_cursorIndexOfQuoteCount) && _cursor.isNull(_cursorIndexOfRetweetCount))) {
              final Integer _tmpLikeCount;
              if (_cursor.isNull(_cursorIndexOfLikeCount)) {
                _tmpLikeCount = null;
              } else {
                _tmpLikeCount = _cursor.getInt(_cursorIndexOfLikeCount);
              }
              final Integer _tmpReplyCount;
              if (_cursor.isNull(_cursorIndexOfReplyCount)) {
                _tmpReplyCount = null;
              } else {
                _tmpReplyCount = _cursor.getInt(_cursorIndexOfReplyCount);
              }
              final Integer _tmpQuoteCount;
              if (_cursor.isNull(_cursorIndexOfQuoteCount)) {
                _tmpQuoteCount = null;
              } else {
                _tmpQuoteCount = _cursor.getInt(_cursorIndexOfQuoteCount);
              }
              final Integer _tmpRetweetCount;
              if (_cursor.isNull(_cursorIndexOfRetweetCount)) {
                _tmpRetweetCount = null;
              } else {
                _tmpRetweetCount = _cursor.getInt(_cursorIndexOfRetweetCount);
              }
              _tmpPublicMetrics = new PublicMetricsTweetEntity(_tmpLikeCount,_tmpReplyCount,_tmpQuoteCount,_tmpRetweetCount);
            }  else  {
              _tmpPublicMetrics = null;
            }
            _item = new DataItemTweetEntity(_tmpId,_tmpCreatedAt,_tmpText,_tmpAuthorId);
            _item.setPublicMetrics(_tmpPublicMetrics);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<DataItemTweetEntity> getTweetById(final String id) {
    final String _sql = "SELECT * FROM tweetPost WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"tweetPost"}, false, new Callable<DataItemTweetEntity>() {
      @Override
      public DataItemTweetEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(_cursor, "authorId");
          final int _cursorIndexOfLikeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "likeCount");
          final int _cursorIndexOfReplyCount = CursorUtil.getColumnIndexOrThrow(_cursor, "replyCount");
          final int _cursorIndexOfQuoteCount = CursorUtil.getColumnIndexOrThrow(_cursor, "quoteCount");
          final int _cursorIndexOfRetweetCount = CursorUtil.getColumnIndexOrThrow(_cursor, "retweetCount");
          final DataItemTweetEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpAuthorId;
            if (_cursor.isNull(_cursorIndexOfAuthorId)) {
              _tmpAuthorId = null;
            } else {
              _tmpAuthorId = _cursor.getString(_cursorIndexOfAuthorId);
            }
            final PublicMetricsTweetEntity _tmpPublicMetrics;
            if (! (_cursor.isNull(_cursorIndexOfLikeCount) && _cursor.isNull(_cursorIndexOfReplyCount) && _cursor.isNull(_cursorIndexOfQuoteCount) && _cursor.isNull(_cursorIndexOfRetweetCount))) {
              final Integer _tmpLikeCount;
              if (_cursor.isNull(_cursorIndexOfLikeCount)) {
                _tmpLikeCount = null;
              } else {
                _tmpLikeCount = _cursor.getInt(_cursorIndexOfLikeCount);
              }
              final Integer _tmpReplyCount;
              if (_cursor.isNull(_cursorIndexOfReplyCount)) {
                _tmpReplyCount = null;
              } else {
                _tmpReplyCount = _cursor.getInt(_cursorIndexOfReplyCount);
              }
              final Integer _tmpQuoteCount;
              if (_cursor.isNull(_cursorIndexOfQuoteCount)) {
                _tmpQuoteCount = null;
              } else {
                _tmpQuoteCount = _cursor.getInt(_cursorIndexOfQuoteCount);
              }
              final Integer _tmpRetweetCount;
              if (_cursor.isNull(_cursorIndexOfRetweetCount)) {
                _tmpRetweetCount = null;
              } else {
                _tmpRetweetCount = _cursor.getInt(_cursorIndexOfRetweetCount);
              }
              _tmpPublicMetrics = new PublicMetricsTweetEntity(_tmpLikeCount,_tmpReplyCount,_tmpQuoteCount,_tmpRetweetCount);
            }  else  {
              _tmpPublicMetrics = null;
            }
            _result = new DataItemTweetEntity(_tmpId,_tmpCreatedAt,_tmpText,_tmpAuthorId);
            _result.setPublicMetrics(_tmpPublicMetrics);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<UserItemsTweetEntity>> getAllTweetProfile() {
    final String _sql = "SELECT * FROM tweetProfile";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"tweetProfile"}, false, new Callable<List<UserItemsTweetEntity>>() {
      @Override
      public List<UserItemsTweetEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(_cursor, "authorId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfProfileImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "profile_image_url");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final List<UserItemsTweetEntity> _result = new ArrayList<UserItemsTweetEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserItemsTweetEntity _item;
            final String _tmpAuthorId;
            if (_cursor.isNull(_cursorIndexOfAuthorId)) {
              _tmpAuthorId = null;
            } else {
              _tmpAuthorId = _cursor.getString(_cursorIndexOfAuthorId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpProfileImageUrl;
            if (_cursor.isNull(_cursorIndexOfProfileImageUrl)) {
              _tmpProfileImageUrl = null;
            } else {
              _tmpProfileImageUrl = _cursor.getString(_cursorIndexOfProfileImageUrl);
            }
            final String _tmpUsername;
            if (_cursor.isNull(_cursorIndexOfUsername)) {
              _tmpUsername = null;
            } else {
              _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            }
            _item = new UserItemsTweetEntity(_tmpAuthorId,_tmpName,_tmpProfileImageUrl,_tmpUsername);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<TweetEntity>> getAllTweets() {
    final String _sql = "SELECT tweetProfile.authorId as authorId, tweetProfile.name as name, tweetProfile.profile_image_url as imageUrl, tweetProfile.username as username, tweetPost.text as text, tweetPost.created_at as date, tweetPost.likeCount as likeCount, tweetPost.quoteCount as quoteCount, tweetPost.replyCount as replyCount, tweetPost.retweetCount as retweetCount FROM tweetProfile, tweetPost WHERE tweetProfile.authorId = tweetPost.authorId";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"tweetProfile","tweetPost"}, false, new Callable<List<TweetEntity>>() {
      @Override
      public List<TweetEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(_cursor, "authorId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfLikeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "likeCount");
          final int _cursorIndexOfQuoteCount = CursorUtil.getColumnIndexOrThrow(_cursor, "quoteCount");
          final int _cursorIndexOfReplyCount = CursorUtil.getColumnIndexOrThrow(_cursor, "replyCount");
          final int _cursorIndexOfRetweetCount = CursorUtil.getColumnIndexOrThrow(_cursor, "retweetCount");
          final List<TweetEntity> _result = new ArrayList<TweetEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TweetEntity _item;
            final String _tmpAuthorId;
            if (_cursor.isNull(_cursorIndexOfAuthorId)) {
              _tmpAuthorId = null;
            } else {
              _tmpAuthorId = _cursor.getString(_cursorIndexOfAuthorId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpUsername;
            if (_cursor.isNull(_cursorIndexOfUsername)) {
              _tmpUsername = null;
            } else {
              _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpLikeCount;
            _tmpLikeCount = _cursor.getInt(_cursorIndexOfLikeCount);
            final int _tmpQuoteCount;
            _tmpQuoteCount = _cursor.getInt(_cursorIndexOfQuoteCount);
            final int _tmpReplyCount;
            _tmpReplyCount = _cursor.getInt(_cursorIndexOfReplyCount);
            final int _tmpRetweetCount;
            _tmpRetweetCount = _cursor.getInt(_cursorIndexOfRetweetCount);
            _item = new TweetEntity(_tmpAuthorId,_tmpName,_tmpImageUrl,_tmpUsername,_tmpDate,_tmpText,_tmpLikeCount,_tmpQuoteCount,_tmpReplyCount,_tmpRetweetCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
