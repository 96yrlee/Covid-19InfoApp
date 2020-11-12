package com.example.covid19appretrotest.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ZoneDao_Impl implements ZoneDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Zone> __insertionAdapterOfZone;

  private final EntityDeletionOrUpdateAdapter<Zone> __deletionAdapterOfZone;

  private final EntityDeletionOrUpdateAdapter<Zone> __updateAdapterOfZone;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteZone;

  public ZoneDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfZone = new EntityInsertionAdapter<Zone>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `zone_stats_table` (`COLUMN_date`,`COLUMN_ZONENAME`,`COLUMN_timeStamp`,`COLUMN_totalCases`,`COLUMN_totalDeaths`,`COLUMN_totalRecovered`,`COLUMN_totalActive`,`COLUMN_todayCases`,`COLUMN_todayRecovered`,`COLUMN_todayDeaths`,`COLUMN_webserviceUpdated`,`COLUMN_population`,`COLUMN_tests`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Zone value) {
        if (value.getDate() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDate());
        }
        if (value.zoneName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.zoneName);
        }
        stmt.bindLong(3, value.getTimeStamp());
        stmt.bindLong(4, value.getTotalCases());
        stmt.bindLong(5, value.getTotalDeaths());
        stmt.bindLong(6, value.getTotalRecovered());
        stmt.bindLong(7, value.getTotalActive());
        stmt.bindLong(8, value.getTodayCases());
        stmt.bindLong(9, value.getTodayRecovered());
        stmt.bindLong(10, value.getTodayDeaths());
        stmt.bindLong(11, value.getWebserviceUpdated());
        stmt.bindLong(12, value.getPopulation());
        stmt.bindLong(13, value.getTests());
      }
    };
    this.__deletionAdapterOfZone = new EntityDeletionOrUpdateAdapter<Zone>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `zone_stats_table` WHERE `COLUMN_ZONENAME` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Zone value) {
        if (value.zoneName == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.zoneName);
        }
      }
    };
    this.__updateAdapterOfZone = new EntityDeletionOrUpdateAdapter<Zone>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `zone_stats_table` SET `COLUMN_date` = ?,`COLUMN_ZONENAME` = ?,`COLUMN_timeStamp` = ?,`COLUMN_totalCases` = ?,`COLUMN_totalDeaths` = ?,`COLUMN_totalRecovered` = ?,`COLUMN_totalActive` = ?,`COLUMN_todayCases` = ?,`COLUMN_todayRecovered` = ?,`COLUMN_todayDeaths` = ?,`COLUMN_webserviceUpdated` = ?,`COLUMN_population` = ?,`COLUMN_tests` = ? WHERE `COLUMN_ZONENAME` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Zone value) {
        if (value.getDate() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDate());
        }
        if (value.zoneName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.zoneName);
        }
        stmt.bindLong(3, value.getTimeStamp());
        stmt.bindLong(4, value.getTotalCases());
        stmt.bindLong(5, value.getTotalDeaths());
        stmt.bindLong(6, value.getTotalRecovered());
        stmt.bindLong(7, value.getTotalActive());
        stmt.bindLong(8, value.getTodayCases());
        stmt.bindLong(9, value.getTodayRecovered());
        stmt.bindLong(10, value.getTodayDeaths());
        stmt.bindLong(11, value.getWebserviceUpdated());
        stmt.bindLong(12, value.getPopulation());
        stmt.bindLong(13, value.getTests());
        if (value.zoneName == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.zoneName);
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM zone_stats_table";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteZone = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM zone_stats_table WHERE COLUMN_ZONENAME = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Zone mZone) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfZone.insert(mZone);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int delete(final Zone mZone) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfZone.handle(mZone);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int update(final Zone mZone) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfZone.handle(mZone);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public void deleteZone(final String zoneName) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteZone.acquire();
    int _argIndex = 1;
    if (zoneName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, zoneName);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteZone.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Zone>> getAllZonesAndDays() {
    final String _sql = "SELECT * FROM zone_stats_table ORDER BY COLUMN_ZONENAME ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"zone_stats_table"}, false, new Callable<List<Zone>>() {
      @Override
      public List<Zone> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_date");
          final int _cursorIndexOfZoneName = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_ZONENAME");
          final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_timeStamp");
          final int _cursorIndexOfTotalCases = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalCases");
          final int _cursorIndexOfTotalDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalDeaths");
          final int _cursorIndexOfTotalRecovered = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalRecovered");
          final int _cursorIndexOfTotalActive = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalActive");
          final int _cursorIndexOfTodayCases = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_todayCases");
          final int _cursorIndexOfTodayRecovered = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_todayRecovered");
          final int _cursorIndexOfTodayDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_todayDeaths");
          final int _cursorIndexOfWebserviceUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_webserviceUpdated");
          final int _cursorIndexOfPopulation = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_population");
          final int _cursorIndexOfTests = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_tests");
          final List<Zone> _result = new ArrayList<Zone>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Zone _item;
            _item = new Zone();
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            _item.setDate(_tmpDate);
            _item.zoneName = _cursor.getString(_cursorIndexOfZoneName);
            final long _tmpTimeStamp;
            _tmpTimeStamp = _cursor.getLong(_cursorIndexOfTimeStamp);
            _item.setTimeStamp(_tmpTimeStamp);
            final int _tmpTotalCases;
            _tmpTotalCases = _cursor.getInt(_cursorIndexOfTotalCases);
            _item.setTotalCases(_tmpTotalCases);
            final int _tmpTotalDeaths;
            _tmpTotalDeaths = _cursor.getInt(_cursorIndexOfTotalDeaths);
            _item.setTotalDeaths(_tmpTotalDeaths);
            final int _tmpTotalRecovered;
            _tmpTotalRecovered = _cursor.getInt(_cursorIndexOfTotalRecovered);
            _item.setTotalRecovered(_tmpTotalRecovered);
            final int _tmpTotalActive;
            _tmpTotalActive = _cursor.getInt(_cursorIndexOfTotalActive);
            _item.setTotalActive(_tmpTotalActive);
            final int _tmpTodayCases;
            _tmpTodayCases = _cursor.getInt(_cursorIndexOfTodayCases);
            _item.setTodayCases(_tmpTodayCases);
            final int _tmpTodayRecovered;
            _tmpTodayRecovered = _cursor.getInt(_cursorIndexOfTodayRecovered);
            _item.setTodayRecovered(_tmpTodayRecovered);
            final int _tmpTodayDeaths;
            _tmpTodayDeaths = _cursor.getInt(_cursorIndexOfTodayDeaths);
            _item.setTodayDeaths(_tmpTodayDeaths);
            final long _tmpWebserviceUpdated;
            _tmpWebserviceUpdated = _cursor.getLong(_cursorIndexOfWebserviceUpdated);
            _item.setWebserviceUpdated(_tmpWebserviceUpdated);
            final long _tmpPopulation;
            _tmpPopulation = _cursor.getLong(_cursorIndexOfPopulation);
            _item.setPopulation(_tmpPopulation);
            final long _tmpTests;
            _tmpTests = _cursor.getLong(_cursorIndexOfTests);
            _item.setTests(_tmpTests);
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
  public LiveData<List<Zone>> getAllDatedZones(final String date) {
    final String _sql = "SELECT * FROM zone_stats_table WHERE COLUMN_date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"zone_stats_table"}, false, new Callable<List<Zone>>() {
      @Override
      public List<Zone> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_date");
          final int _cursorIndexOfZoneName = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_ZONENAME");
          final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_timeStamp");
          final int _cursorIndexOfTotalCases = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalCases");
          final int _cursorIndexOfTotalDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalDeaths");
          final int _cursorIndexOfTotalRecovered = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalRecovered");
          final int _cursorIndexOfTotalActive = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalActive");
          final int _cursorIndexOfTodayCases = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_todayCases");
          final int _cursorIndexOfTodayRecovered = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_todayRecovered");
          final int _cursorIndexOfTodayDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_todayDeaths");
          final int _cursorIndexOfWebserviceUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_webserviceUpdated");
          final int _cursorIndexOfPopulation = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_population");
          final int _cursorIndexOfTests = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_tests");
          final List<Zone> _result = new ArrayList<Zone>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Zone _item;
            _item = new Zone();
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            _item.setDate(_tmpDate);
            _item.zoneName = _cursor.getString(_cursorIndexOfZoneName);
            final long _tmpTimeStamp;
            _tmpTimeStamp = _cursor.getLong(_cursorIndexOfTimeStamp);
            _item.setTimeStamp(_tmpTimeStamp);
            final int _tmpTotalCases;
            _tmpTotalCases = _cursor.getInt(_cursorIndexOfTotalCases);
            _item.setTotalCases(_tmpTotalCases);
            final int _tmpTotalDeaths;
            _tmpTotalDeaths = _cursor.getInt(_cursorIndexOfTotalDeaths);
            _item.setTotalDeaths(_tmpTotalDeaths);
            final int _tmpTotalRecovered;
            _tmpTotalRecovered = _cursor.getInt(_cursorIndexOfTotalRecovered);
            _item.setTotalRecovered(_tmpTotalRecovered);
            final int _tmpTotalActive;
            _tmpTotalActive = _cursor.getInt(_cursorIndexOfTotalActive);
            _item.setTotalActive(_tmpTotalActive);
            final int _tmpTodayCases;
            _tmpTodayCases = _cursor.getInt(_cursorIndexOfTodayCases);
            _item.setTodayCases(_tmpTodayCases);
            final int _tmpTodayRecovered;
            _tmpTodayRecovered = _cursor.getInt(_cursorIndexOfTodayRecovered);
            _item.setTodayRecovered(_tmpTodayRecovered);
            final int _tmpTodayDeaths;
            _tmpTodayDeaths = _cursor.getInt(_cursorIndexOfTodayDeaths);
            _item.setTodayDeaths(_tmpTodayDeaths);
            final long _tmpWebserviceUpdated;
            _tmpWebserviceUpdated = _cursor.getLong(_cursorIndexOfWebserviceUpdated);
            _item.setWebserviceUpdated(_tmpWebserviceUpdated);
            final long _tmpPopulation;
            _tmpPopulation = _cursor.getLong(_cursorIndexOfPopulation);
            _item.setPopulation(_tmpPopulation);
            final long _tmpTests;
            _tmpTests = _cursor.getLong(_cursorIndexOfTests);
            _item.setTests(_tmpTests);
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
  public LiveData<Zone> getSpecificZone(final String zoneName) {
    final String _sql = "SELECT * FROM zone_stats_table WHERE COLUMN_ZONENAME = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (zoneName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, zoneName);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"zone_stats_table"}, false, new Callable<Zone>() {
      @Override
      public Zone call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_date");
          final int _cursorIndexOfZoneName = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_ZONENAME");
          final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_timeStamp");
          final int _cursorIndexOfTotalCases = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalCases");
          final int _cursorIndexOfTotalDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalDeaths");
          final int _cursorIndexOfTotalRecovered = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalRecovered");
          final int _cursorIndexOfTotalActive = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalActive");
          final int _cursorIndexOfTodayCases = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_todayCases");
          final int _cursorIndexOfTodayRecovered = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_todayRecovered");
          final int _cursorIndexOfTodayDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_todayDeaths");
          final int _cursorIndexOfWebserviceUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_webserviceUpdated");
          final int _cursorIndexOfPopulation = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_population");
          final int _cursorIndexOfTests = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_tests");
          final Zone _result;
          if(_cursor.moveToFirst()) {
            _result = new Zone();
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            _result.setDate(_tmpDate);
            _result.zoneName = _cursor.getString(_cursorIndexOfZoneName);
            final long _tmpTimeStamp;
            _tmpTimeStamp = _cursor.getLong(_cursorIndexOfTimeStamp);
            _result.setTimeStamp(_tmpTimeStamp);
            final int _tmpTotalCases;
            _tmpTotalCases = _cursor.getInt(_cursorIndexOfTotalCases);
            _result.setTotalCases(_tmpTotalCases);
            final int _tmpTotalDeaths;
            _tmpTotalDeaths = _cursor.getInt(_cursorIndexOfTotalDeaths);
            _result.setTotalDeaths(_tmpTotalDeaths);
            final int _tmpTotalRecovered;
            _tmpTotalRecovered = _cursor.getInt(_cursorIndexOfTotalRecovered);
            _result.setTotalRecovered(_tmpTotalRecovered);
            final int _tmpTotalActive;
            _tmpTotalActive = _cursor.getInt(_cursorIndexOfTotalActive);
            _result.setTotalActive(_tmpTotalActive);
            final int _tmpTodayCases;
            _tmpTodayCases = _cursor.getInt(_cursorIndexOfTodayCases);
            _result.setTodayCases(_tmpTodayCases);
            final int _tmpTodayRecovered;
            _tmpTodayRecovered = _cursor.getInt(_cursorIndexOfTodayRecovered);
            _result.setTodayRecovered(_tmpTodayRecovered);
            final int _tmpTodayDeaths;
            _tmpTodayDeaths = _cursor.getInt(_cursorIndexOfTodayDeaths);
            _result.setTodayDeaths(_tmpTodayDeaths);
            final long _tmpWebserviceUpdated;
            _tmpWebserviceUpdated = _cursor.getLong(_cursorIndexOfWebserviceUpdated);
            _result.setWebserviceUpdated(_tmpWebserviceUpdated);
            final long _tmpPopulation;
            _tmpPopulation = _cursor.getLong(_cursorIndexOfPopulation);
            _result.setPopulation(_tmpPopulation);
            final long _tmpTests;
            _tmpTests = _cursor.getLong(_cursorIndexOfTests);
            _result.setTests(_tmpTests);
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
  public int hasTimedZone(final String zoneName, final long maxRefreshTime) {
    final String _sql = "SELECT COUNT(*) FROM zone_stats_table WHERE COLUMN_ZONENAME = ? AND COLUMN_timeStamp > ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (zoneName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, zoneName);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, maxRefreshTime);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getZoneFromToday(final String date, final String zoneName) {
    final String _sql = "SELECT COUNT(*) FROM zone_stats_table WHERE COLUMN_ZONENAME = ? AND COLUMN_date = ? ORDER BY COLUMN_ZONENAME DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (zoneName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, zoneName);
    }
    _argIndex = 2;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getSize() {
    final String _sql = "SELECT COUNT(*) FROM zone_stats_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getCountryNames(final String global) {
    final String _sql = "SELECT COLUMN_ZONENAME FROM zone_stats_table WHERE COLUMN_ZONENAME != ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (global == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, global);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Cursor selectAll() {
    final String _sql = "SELECT * FROM zone_stats_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _tmpResult = __db.query(_statement);
    return _tmpResult;
  }

  @Override
  public Cursor selectbyName(final String zoneName) {
    final String _sql = "SELECT * FROM zone_stats_table WHERE COLUMN_ZONENAME= ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (zoneName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, zoneName);
    }
    final Cursor _tmpResult = __db.query(_statement);
    return _tmpResult;
  }

  @Override
  public Zone getZone(final String zoneName) {
    final String _sql = "SELECT * FROM zone_stats_table WHERE COLUMN_ZONENAME = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (zoneName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, zoneName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_date");
      final int _cursorIndexOfZoneName = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_ZONENAME");
      final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_timeStamp");
      final int _cursorIndexOfTotalCases = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalCases");
      final int _cursorIndexOfTotalDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalDeaths");
      final int _cursorIndexOfTotalRecovered = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalRecovered");
      final int _cursorIndexOfTotalActive = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_totalActive");
      final int _cursorIndexOfTodayCases = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_todayCases");
      final int _cursorIndexOfTodayRecovered = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_todayRecovered");
      final int _cursorIndexOfTodayDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_todayDeaths");
      final int _cursorIndexOfWebserviceUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_webserviceUpdated");
      final int _cursorIndexOfPopulation = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_population");
      final int _cursorIndexOfTests = CursorUtil.getColumnIndexOrThrow(_cursor, "COLUMN_tests");
      final Zone _result;
      if(_cursor.moveToFirst()) {
        _result = new Zone();
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        _result.setDate(_tmpDate);
        _result.zoneName = _cursor.getString(_cursorIndexOfZoneName);
        final long _tmpTimeStamp;
        _tmpTimeStamp = _cursor.getLong(_cursorIndexOfTimeStamp);
        _result.setTimeStamp(_tmpTimeStamp);
        final int _tmpTotalCases;
        _tmpTotalCases = _cursor.getInt(_cursorIndexOfTotalCases);
        _result.setTotalCases(_tmpTotalCases);
        final int _tmpTotalDeaths;
        _tmpTotalDeaths = _cursor.getInt(_cursorIndexOfTotalDeaths);
        _result.setTotalDeaths(_tmpTotalDeaths);
        final int _tmpTotalRecovered;
        _tmpTotalRecovered = _cursor.getInt(_cursorIndexOfTotalRecovered);
        _result.setTotalRecovered(_tmpTotalRecovered);
        final int _tmpTotalActive;
        _tmpTotalActive = _cursor.getInt(_cursorIndexOfTotalActive);
        _result.setTotalActive(_tmpTotalActive);
        final int _tmpTodayCases;
        _tmpTodayCases = _cursor.getInt(_cursorIndexOfTodayCases);
        _result.setTodayCases(_tmpTodayCases);
        final int _tmpTodayRecovered;
        _tmpTodayRecovered = _cursor.getInt(_cursorIndexOfTodayRecovered);
        _result.setTodayRecovered(_tmpTodayRecovered);
        final int _tmpTodayDeaths;
        _tmpTodayDeaths = _cursor.getInt(_cursorIndexOfTodayDeaths);
        _result.setTodayDeaths(_tmpTodayDeaths);
        final long _tmpWebserviceUpdated;
        _tmpWebserviceUpdated = _cursor.getLong(_cursorIndexOfWebserviceUpdated);
        _result.setWebserviceUpdated(_tmpWebserviceUpdated);
        final long _tmpPopulation;
        _tmpPopulation = _cursor.getLong(_cursorIndexOfPopulation);
        _result.setPopulation(_tmpPopulation);
        final long _tmpTests;
        _tmpTests = _cursor.getLong(_cursorIndexOfTests);
        _result.setTests(_tmpTests);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
