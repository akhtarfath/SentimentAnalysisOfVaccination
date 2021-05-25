package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleCovidEntity;
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleVaccinesEntity;
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.DataItemTweetEntity;
import com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TeamsEntity;
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
        return "INSERT OR REPLACE INTO `tweetPost` (`id`,`created_at`,`text`,`author_id`) VALUES (?,?,?,?)";
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
  public DataSource.Factory<Integer, DataItemTweetEntity> getAllTweet() {
    final String _sql = "SELECT * FROM tweetPost";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new DataSource.Factory<Integer, DataItemTweetEntity>() {
      @Override
      public LimitOffsetDataSource<DataItemTweetEntity> create() {
        return new LimitOffsetDataSource<DataItemTweetEntity>(__db, _statement, false, true , "tweetPost") {
          @Override
          protected List<DataItemTweetEntity> convertRows(Cursor cursor) {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
            final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(cursor, "created_at");
            final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(cursor, "text");
            final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(cursor, "author_id");
            final List<DataItemTweetEntity> _res = new ArrayList<DataItemTweetEntity>(cursor.getCount());
            while(cursor.moveToNext()) {
              final DataItemTweetEntity _item;
              final String _tmpId;
              if (cursor.isNull(_cursorIndexOfId)) {
                _tmpId = null;
              } else {
                _tmpId = cursor.getString(_cursorIndexOfId);
              }
              final String _tmpCreatedAt;
              if (cursor.isNull(_cursorIndexOfCreatedAt)) {
                _tmpCreatedAt = null;
              } else {
                _tmpCreatedAt = cursor.getString(_cursorIndexOfCreatedAt);
              }
              final String _tmpText;
              if (cursor.isNull(_cursorIndexOfText)) {
                _tmpText = null;
              } else {
                _tmpText = cursor.getString(_cursorIndexOfText);
              }
              final String _tmpAuthorId;
              if (cursor.isNull(_cursorIndexOfAuthorId)) {
                _tmpAuthorId = null;
              } else {
                _tmpAuthorId = cursor.getString(_cursorIndexOfAuthorId);
              }
              _item = new DataItemTweetEntity(_tmpId,_tmpCreatedAt,_tmpText,_tmpAuthorId);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
