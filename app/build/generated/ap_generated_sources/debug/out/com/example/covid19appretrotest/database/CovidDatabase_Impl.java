package com.example.covid19appretrotest.database;

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
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CovidDatabase_Impl extends CovidDatabase {
  private volatile ZoneDao _zoneDao;

  private volatile CountriesDao _countriesDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `zone_stats_table` (`COLUMN_date` TEXT, `COLUMN_ZONENAME` TEXT NOT NULL, `COLUMN_timeStamp` INTEGER NOT NULL, `COLUMN_totalCases` INTEGER NOT NULL, `COLUMN_totalDeaths` INTEGER NOT NULL, `COLUMN_totalRecovered` INTEGER NOT NULL, `COLUMN_totalActive` INTEGER NOT NULL, `COLUMN_todayCases` INTEGER NOT NULL, `COLUMN_todayRecovered` INTEGER NOT NULL, `COLUMN_todayDeaths` INTEGER NOT NULL, `COLUMN_webserviceUpdated` INTEGER NOT NULL, `COLUMN_population` INTEGER NOT NULL, `COLUMN_tests` INTEGER NOT NULL, PRIMARY KEY(`COLUMN_ZONENAME`))");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_zone_stats_table_COLUMN_date` ON `zone_stats_table` (`COLUMN_date`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_zone_stats_table_COLUMN_ZONENAME` ON `zone_stats_table` (`COLUMN_ZONENAME`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `countries_list_table` (`ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `country` TEXT, `totalCases` INTEGER NOT NULL, `totalDeaths` INTEGER NOT NULL, `totalRecovered` INTEGER NOT NULL, `totalActive` INTEGER NOT NULL, `todayCases` INTEGER NOT NULL, `todayRecovered` INTEGER NOT NULL, `todayDeaths` INTEGER NOT NULL, `webserviceUpdated` INTEGER NOT NULL, `population` INTEGER NOT NULL, `tests` INTEGER NOT NULL, `ISOid` INTEGER NOT NULL, `iso2` TEXT, `iso3` TEXT, `flagURL` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '733783c2918d4fdd72127560778f23aa')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `zone_stats_table`");
        _db.execSQL("DROP TABLE IF EXISTS `countries_list_table`");
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
        final HashMap<String, TableInfo.Column> _columnsZoneStatsTable = new HashMap<String, TableInfo.Column>(13);
        _columnsZoneStatsTable.put("COLUMN_date", new TableInfo.Column("COLUMN_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsZoneStatsTable.put("COLUMN_ZONENAME", new TableInfo.Column("COLUMN_ZONENAME", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsZoneStatsTable.put("COLUMN_timeStamp", new TableInfo.Column("COLUMN_timeStamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsZoneStatsTable.put("COLUMN_totalCases", new TableInfo.Column("COLUMN_totalCases", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsZoneStatsTable.put("COLUMN_totalDeaths", new TableInfo.Column("COLUMN_totalDeaths", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsZoneStatsTable.put("COLUMN_totalRecovered", new TableInfo.Column("COLUMN_totalRecovered", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsZoneStatsTable.put("COLUMN_totalActive", new TableInfo.Column("COLUMN_totalActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsZoneStatsTable.put("COLUMN_todayCases", new TableInfo.Column("COLUMN_todayCases", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsZoneStatsTable.put("COLUMN_todayRecovered", new TableInfo.Column("COLUMN_todayRecovered", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsZoneStatsTable.put("COLUMN_todayDeaths", new TableInfo.Column("COLUMN_todayDeaths", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsZoneStatsTable.put("COLUMN_webserviceUpdated", new TableInfo.Column("COLUMN_webserviceUpdated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsZoneStatsTable.put("COLUMN_population", new TableInfo.Column("COLUMN_population", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsZoneStatsTable.put("COLUMN_tests", new TableInfo.Column("COLUMN_tests", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysZoneStatsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesZoneStatsTable = new HashSet<TableInfo.Index>(2);
        _indicesZoneStatsTable.add(new TableInfo.Index("index_zone_stats_table_COLUMN_date", false, Arrays.asList("COLUMN_date")));
        _indicesZoneStatsTable.add(new TableInfo.Index("index_zone_stats_table_COLUMN_ZONENAME", false, Arrays.asList("COLUMN_ZONENAME")));
        final TableInfo _infoZoneStatsTable = new TableInfo("zone_stats_table", _columnsZoneStatsTable, _foreignKeysZoneStatsTable, _indicesZoneStatsTable);
        final TableInfo _existingZoneStatsTable = TableInfo.read(_db, "zone_stats_table");
        if (! _infoZoneStatsTable.equals(_existingZoneStatsTable)) {
          return new RoomOpenHelper.ValidationResult(false, "zone_stats_table(com.example.covid19appretrotest.database.Zone).\n"
                  + " Expected:\n" + _infoZoneStatsTable + "\n"
                  + " Found:\n" + _existingZoneStatsTable);
        }
        final HashMap<String, TableInfo.Column> _columnsCountriesListTable = new HashMap<String, TableInfo.Column>(16);
        _columnsCountriesListTable.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("country", new TableInfo.Column("country", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("totalCases", new TableInfo.Column("totalCases", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("totalDeaths", new TableInfo.Column("totalDeaths", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("totalRecovered", new TableInfo.Column("totalRecovered", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("totalActive", new TableInfo.Column("totalActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("todayCases", new TableInfo.Column("todayCases", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("todayRecovered", new TableInfo.Column("todayRecovered", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("todayDeaths", new TableInfo.Column("todayDeaths", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("webserviceUpdated", new TableInfo.Column("webserviceUpdated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("population", new TableInfo.Column("population", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("tests", new TableInfo.Column("tests", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("ISOid", new TableInfo.Column("ISOid", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("iso2", new TableInfo.Column("iso2", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("iso3", new TableInfo.Column("iso3", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCountriesListTable.put("flagURL", new TableInfo.Column("flagURL", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCountriesListTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCountriesListTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCountriesListTable = new TableInfo("countries_list_table", _columnsCountriesListTable, _foreignKeysCountriesListTable, _indicesCountriesListTable);
        final TableInfo _existingCountriesListTable = TableInfo.read(_db, "countries_list_table");
        if (! _infoCountriesListTable.equals(_existingCountriesListTable)) {
          return new RoomOpenHelper.ValidationResult(false, "countries_list_table(com.example.covid19appretrotest.database.Countries).\n"
                  + " Expected:\n" + _infoCountriesListTable + "\n"
                  + " Found:\n" + _existingCountriesListTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "733783c2918d4fdd72127560778f23aa", "4dcf2119be28c06d4f321b4603e751bf");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "zone_stats_table","countries_list_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `zone_stats_table`");
      _db.execSQL("DELETE FROM `countries_list_table`");
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
  public ZoneDao mZoneDao() {
    if (_zoneDao != null) {
      return _zoneDao;
    } else {
      synchronized(this) {
        if(_zoneDao == null) {
          _zoneDao = new ZoneDao_Impl(this);
        }
        return _zoneDao;
      }
    }
  }

  @Override
  public CountriesDao mCountryDao() {
    if (_countriesDao != null) {
      return _countriesDao;
    } else {
      synchronized(this) {
        if(_countriesDao == null) {
          _countriesDao = new CountriesDao_Impl(this);
        }
        return _countriesDao;
      }
    }
  }
}
