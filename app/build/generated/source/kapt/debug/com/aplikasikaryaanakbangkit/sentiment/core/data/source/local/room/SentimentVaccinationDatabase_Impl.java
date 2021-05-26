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
import java.util.Arrays;
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
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `articleCovid` (`url` TEXT NOT NULL, `author` TEXT, `urlToImage` TEXT, `description` TEXT, `title` TEXT, `publishedAt` TEXT, `content` TEXT, PRIMARY KEY(`url`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `articleVaccine` (`url` TEXT NOT NULL, `author` TEXT, `urlToImage` TEXT, `description` TEXT, `title` TEXT, `publishedAt` TEXT, `content` TEXT, PRIMARY KEY(`url`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `teams` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `urlPicture` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `tweetPost` (`id` TEXT NOT NULL, `created_at` TEXT NOT NULL, `text` TEXT NOT NULL, `authorId` TEXT NOT NULL, `likeCount` INTEGER, `replyCount` INTEGER, `quoteCount` INTEGER, `retweetCount` INTEGER, PRIMARY KEY(`id`, `authorId`), FOREIGN KEY(`authorId`) REFERENCES `tweetProfile`(`authorId`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_tweetPost_id` ON `tweetPost` (`id`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_tweetPost_authorId` ON `tweetPost` (`authorId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `tweetProfile` (`authorId` TEXT NOT NULL, `name` TEXT NOT NULL, `profile_image_url` TEXT NOT NULL, `username` TEXT NOT NULL, PRIMARY KEY(`authorId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '4d86075213a3b299bfd32865482dd067')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `articleCovid`");
        _db.execSQL("DROP TABLE IF EXISTS `articleVaccine`");
        _db.execSQL("DROP TABLE IF EXISTS `teams`");
        _db.execSQL("DROP TABLE IF EXISTS `tweetPost`");
        _db.execSQL("DROP TABLE IF EXISTS `tweetProfile`");
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
        _db.execSQL("PRAGMA foreign_keys = ON");
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
        final HashMap<String, TableInfo.Column> _columnsTweetPost = new HashMap<String, TableInfo.Column>(8);
        _columnsTweetPost.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetPost.put("created_at", new TableInfo.Column("created_at", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetPost.put("text", new TableInfo.Column("text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetPost.put("authorId", new TableInfo.Column("authorId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetPost.put("likeCount", new TableInfo.Column("likeCount", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetPost.put("replyCount", new TableInfo.Column("replyCount", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetPost.put("quoteCount", new TableInfo.Column("quoteCount", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetPost.put("retweetCount", new TableInfo.Column("retweetCount", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTweetPost = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysTweetPost.add(new TableInfo.ForeignKey("tweetProfile", "NO ACTION", "NO ACTION",Arrays.asList("authorId"), Arrays.asList("authorId")));
        final HashSet<TableInfo.Index> _indicesTweetPost = new HashSet<TableInfo.Index>(2);
        _indicesTweetPost.add(new TableInfo.Index("index_tweetPost_id", false, Arrays.asList("id")));
        _indicesTweetPost.add(new TableInfo.Index("index_tweetPost_authorId", false, Arrays.asList("authorId")));
        final TableInfo _infoTweetPost = new TableInfo("tweetPost", _columnsTweetPost, _foreignKeysTweetPost, _indicesTweetPost);
        final TableInfo _existingTweetPost = TableInfo.read(_db, "tweetPost");
        if (! _infoTweetPost.equals(_existingTweetPost)) {
          return new RoomOpenHelper.ValidationResult(false, "tweetPost(com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.DataItemTweetEntity).\n"
                  + " Expected:\n" + _infoTweetPost + "\n"
                  + " Found:\n" + _existingTweetPost);
        }
        final HashMap<String, TableInfo.Column> _columnsTweetProfile = new HashMap<String, TableInfo.Column>(4);
        _columnsTweetProfile.put("authorId", new TableInfo.Column("authorId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetProfile.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetProfile.put("profile_image_url", new TableInfo.Column("profile_image_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTweetProfile.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTweetProfile = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTweetProfile = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTweetProfile = new TableInfo("tweetProfile", _columnsTweetProfile, _foreignKeysTweetProfile, _indicesTweetProfile);
        final TableInfo _existingTweetProfile = TableInfo.read(_db, "tweetProfile");
        if (! _infoTweetProfile.equals(_existingTweetProfile)) {
          return new RoomOpenHelper.ValidationResult(false, "tweetProfile(com.aplikasikaryaanakbangkit.sentiment.core.data.source.local.entity.UserItemsTweetEntity).\n"
                  + " Expected:\n" + _infoTweetProfile + "\n"
                  + " Found:\n" + _existingTweetProfile);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "4d86075213a3b299bfd32865482dd067", "fb966efad534c7e6c32b6621f4d8118c");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "articleCovid","articleVaccine","teams","tweetPost","tweetProfile");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `articleCovid`");
      _db.execSQL("DELETE FROM `articleVaccine`");
      _db.execSQL("DELETE FROM `teams`");
      _db.execSQL("DELETE FROM `tweetPost`");
      _db.execSQL("DELETE FROM `tweetProfile`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
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
  public SentimentDao sentimentDao() {
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
