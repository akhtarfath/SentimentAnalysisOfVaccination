package com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.room;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SentimentVaccinationDatabase_Impl extends SentimentVaccinationDatabase {
  private volatile SentimentDao _sentimentDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `articleCovid` (`url` TEXT NOT NULL, `author` TEXT, `urlToImage` TEXT, `description` TEXT, `title` TEXT, `publishedAt` TEXT, `content` TEXT, PRIMARY KEY(`url`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `articleVaccine` (`url` TEXT NOT NULL, `author` TEXT, `urlToImage` TEXT, `description` TEXT, `title` TEXT, `publishedAt` TEXT, `content` TEXT, PRIMARY KEY(`url`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `teams` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `urlPicture` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `tweetPost` (`id` TEXT NOT NULL, `created_at` TEXT NOT NULL, `text` TEXT NOT NULL, `author_id` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c9c054ff310064440c8d8322775dd506')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `articleCovid`");
        _db.execSQL("DROP TABLE IF EXISTS `articleVaccine`");
        _db.execSQL("DROP TABLE IF EXISTS `teams`");
        _db.execSQL("DROP TABLE IF EXISTS `tweetPost`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsArticleCovid = new HashMap<String, TableInfo.Column>(7);
        _columnsArticleCovid.put("url", new TableInfo.Column("url", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticleCovid.put("author", new TableInfo.Column("author", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticleCovid.put("urlToImage", new TableInfo.Column("urlToImage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticleCovid.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticleCovid.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticleCovid.put("publishedAt", new TableInfo.Column("publishedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticleCovid.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysArticleCovid = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesArticleCovid = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoArticleCovid = new TableInfo("articleCovid", _columnsArticleCovid, _foreignKeysArticleCovid, _indicesArticleCovid);
        final TableInfo _existingArticleCovid = TableInfo.read(_db, "articleCovid");
        if (! _infoArticleCovid.equals(_existingArticleCovid)) {
          return new RoomOpenHelper.ValidationResult(false, "articleCovid(com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleCovidEntity).\n"
                  + " Expected:\n" + _infoArticleCovid + "\n"
                  + " Found:\n" + _existingArticleCovid);
        }
        final HashMap<String, TableInfo.Column> _columnsArticleVaccine = new HashMap<String, TableInfo.Column>(7);
        _columnsArticleVaccine.put("url", new TableInfo.Column("url", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticleVaccine.put("author", new TableInfo.Column("author", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticleVaccine.put("urlToImage", new TableInfo.Column("urlToImage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticleVaccine.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticleVaccine.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticleVaccine.put("publishedAt", new TableInfo.Column("publishedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArticleVaccine.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysArticleVaccine = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesArticleVaccine = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoArticleVaccine = new TableInfo("articleVaccine", _columnsArticleVaccine, _foreignKeysArticleVaccine, _indicesArticleVaccine);
        final TableInfo _existingArticleVaccine = TableInfo.read(_db, "articleVaccine");
        if (! _infoArticleVaccine.equals(_existingArticleVaccine)) {
          return new RoomOpenHelper.ValidationResult(false, "articleVaccine(com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.ArticleVaccinesEntity).\n"
                  + " Expected:\n" + _infoArticleVaccine + "\n"
                  + " Found:\n" + _existingArticleVaccine);
        }
        final HashMap<String, TableInfo.Column> _columnsTeams = new HashMap<String, TableInfo.Column>(3);
        _columnsTeams.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeams.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeams.put("urlPicture", new TableInfo.Column("urlPicture", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTeams = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTeams = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTeams = new TableInfo("teams", _columnsTeams, _foreignKeysTeams, _indicesTeams);
        final TableInfo _existingTeams = TableInfo.read(_db, "teams");
        if (! _infoTeams.equals(_existingTeams)) {
          return new RoomOpenHelper.ValidationResult(false, "teams(com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.TeamsEntity).\n"
                  + " Expected:\n" + _infoTeams + "\n"
                  + " Found:\n" + _existingTeams);
        }
        final HashMap<String, TableInfo.Column> _columnsTweetPost = new HashMap<String, TableInfo.Column>(4);
        _columnsTweetPost.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetPost.put("created_at", new TableInfo.Column("created_at", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetPost.put("text", new TableInfo.Column("text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetPost.put("author_id", new TableInfo.Column("author_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTweetPost = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTweetPost = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTweetPost = new TableInfo("tweetPost", _columnsTweetPost, _foreignKeysTweetPost, _indicesTweetPost);
        final TableInfo _existingTweetPost = TableInfo.read(_db, "tweetPost");
        if (! _infoTweetPost.equals(_existingTweetPost)) {
          return new RoomOpenHelper.ValidationResult(false, "tweetPost(com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.DataItemTweetEntity).\n"
                  + " Expected:\n" + _infoTweetPost + "\n"
                  + " Found:\n" + _existingTweetPost);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c9c054ff310064440c8d8322775dd506", "ed30cb19f2371ee8211a4136f592a888");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "articleCovid","articleVaccine","teams","tweetPost");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `articleCovid`");
      _db.execSQL("DELETE FROM `articleVaccine`");
      _db.execSQL("DELETE FROM `teams`");
      _db.execSQL("DELETE FROM `tweetPost`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(SentimentDao.class, SentimentDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public SentimentDao newsDao() {
    if (_sentimentDao != null) {
      return _sentimentDao;
    } else {
      synchronized(this) {
        if(_sentimentDao == null) {
          _sentimentDao = new SentimentDao_Impl(this);
        }
        return _sentimentDao;
      }
    }
  }
}
