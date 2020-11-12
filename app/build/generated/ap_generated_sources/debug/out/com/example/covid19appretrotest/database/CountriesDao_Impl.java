package com.example.covid19appretrotest.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
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
public final class CountriesDao_Impl implements CountriesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Countries> __insertionAdapterOfCountries;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public CountriesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCountries = new EntityInsertionAdapter<Countries>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `countries_list_table` (`ID`,`country`,`totalCases`,`totalDeaths`,`totalRecovered`,`totalActive`,`todayCases`,`todayRecovered`,`todayDeaths`,`webserviceUpdated`,`population`,`tests`,`ISOid`,`iso2`,`iso3`,`flagURL`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Countries value) {
        stmt.bindLong(1, value.getID());
        if (value.getCountry() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCountry());
        }
        stmt.bindLong(3, value.getTotalCases());
        stmt.bindLong(4, value.getTotalDeaths());
        stmt.bindLong(5, value.getTotalRecovered());
        stmt.bindLong(6, value.getTotalActive());
        stmt.bindLong(7, value.getTodayCases());
        stmt.bindLong(8, value.getTodayRecovered());
        stmt.bindLong(9, value.getTodayDeaths());
        stmt.bindLong(10, value.getWebserviceUpdated());
        stmt.bindLong(11, value.getPopulation());
        stmt.bindLong(12, value.getTests());
        stmt.bindLong(13, value.getISOid());
        if (value.getIso2() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getIso2());
        }
        if (value.getIso3() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getIso3());
        }
        if (value.getFlagURL() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getFlagURL());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM countries_list_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Countries mCountries) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCountries.insert(mCountries);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final List<Countries> countriesList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCountries.insert(countriesList);
      __db.setTransactionSuccessful();
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
  public LiveData<List<Countries>> getAllCountries() {
    final String _sql = "SELECT * FROM countries_list_table ORDER BY country DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"countries_list_table"}, false, new Callable<List<Countries>>() {
      @Override
      public List<Countries> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfID = CursorUtil.getColumnIndexOrThrow(_cursor, "ID");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfTotalCases = CursorUtil.getColumnIndexOrThrow(_cursor, "totalCases");
          final int _cursorIndexOfTotalDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDeaths");
          final int _cursorIndexOfTotalRecovered = CursorUtil.getColumnIndexOrThrow(_cursor, "totalRecovered");
          final int _cursorIndexOfTotalActive = CursorUtil.getColumnIndexOrThrow(_cursor, "totalActive");
          final int _cursorIndexOfTodayCases = CursorUtil.getColumnIndexOrThrow(_cursor, "todayCases");
          final int _cursorIndexOfTodayRecovered = CursorUtil.getColumnIndexOrThrow(_cursor, "todayRecovered");
          final int _cursorIndexOfTodayDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "todayDeaths");
          final int _cursorIndexOfWebserviceUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "webserviceUpdated");
          final int _cursorIndexOfPopulation = CursorUtil.getColumnIndexOrThrow(_cursor, "population");
          final int _cursorIndexOfTests = CursorUtil.getColumnIndexOrThrow(_cursor, "tests");
          final int _cursorIndexOfISOid = CursorUtil.getColumnIndexOrThrow(_cursor, "ISOid");
          final int _cursorIndexOfIso2 = CursorUtil.getColumnIndexOrThrow(_cursor, "iso2");
          final int _cursorIndexOfIso3 = CursorUtil.getColumnIndexOrThrow(_cursor, "iso3");
          final int _cursorIndexOfFlagURL = CursorUtil.getColumnIndexOrThrow(_cursor, "flagURL");
          final List<Countries> _result = new ArrayList<Countries>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Countries _item;
            final String _tmpCountry;
            _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            final int _tmpTotalCases;
            _tmpTotalCases = _cursor.getInt(_cursorIndexOfTotalCases);
            final int _tmpTotalDeaths;
            _tmpTotalDeaths = _cursor.getInt(_cursorIndexOfTotalDeaths);
            final int _tmpTotalRecovered;
            _tmpTotalRecovered = _cursor.getInt(_cursorIndexOfTotalRecovered);
            final int _tmpTotalActive;
            _tmpTotalActive = _cursor.getInt(_cursorIndexOfTotalActive);
            final int _tmpTodayCases;
            _tmpTodayCases = _cursor.getInt(_cursorIndexOfTodayCases);
            final int _tmpTodayRecovered;
            _tmpTodayRecovered = _cursor.getInt(_cursorIndexOfTodayRecovered);
            final int _tmpTodayDeaths;
            _tmpTodayDeaths = _cursor.getInt(_cursorIndexOfTodayDeaths);
            final long _tmpWebserviceUpdated;
            _tmpWebserviceUpdated = _cursor.getLong(_cursorIndexOfWebserviceUpdated);
            final long _tmpPopulation;
            _tmpPopulation = _cursor.getLong(_cursorIndexOfPopulation);
            final long _tmpTests;
            _tmpTests = _cursor.getLong(_cursorIndexOfTests);
            final int _tmpISOid;
            _tmpISOid = _cursor.getInt(_cursorIndexOfISOid);
            final String _tmpIso2;
            _tmpIso2 = _cursor.getString(_cursorIndexOfIso2);
            final String _tmpIso3;
            _tmpIso3 = _cursor.getString(_cursorIndexOfIso3);
            final String _tmpFlagURL;
            _tmpFlagURL = _cursor.getString(_cursorIndexOfFlagURL);
            _item = new Countries(_tmpCountry,_tmpFlagURL,_tmpISOid,_tmpIso2,_tmpIso3,_tmpTotalCases,_tmpTotalDeaths,_tmpTotalRecovered,_tmpTotalActive,_tmpTodayCases,_tmpTodayRecovered,_tmpTodayDeaths,_tmpWebserviceUpdated,_tmpPopulation,_tmpTests);
            final int _tmpID;
            _tmpID = _cursor.getInt(_cursorIndexOfID);
            _item.setID(_tmpID);
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
  public List<Countries> getAllSearch(final String search) {
    final String _sql = "SELECT * FROM countries_list_table WHERE country LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (search == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, search);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfID = CursorUtil.getColumnIndexOrThrow(_cursor, "ID");
      final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
      final int _cursorIndexOfTotalCases = CursorUtil.getColumnIndexOrThrow(_cursor, "totalCases");
      final int _cursorIndexOfTotalDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDeaths");
      final int _cursorIndexOfTotalRecovered = CursorUtil.getColumnIndexOrThrow(_cursor, "totalRecovered");
      final int _cursorIndexOfTotalActive = CursorUtil.getColumnIndexOrThrow(_cursor, "totalActive");
      final int _cursorIndexOfTodayCases = CursorUtil.getColumnIndexOrThrow(_cursor, "todayCases");
      final int _cursorIndexOfTodayRecovered = CursorUtil.getColumnIndexOrThrow(_cursor, "todayRecovered");
      final int _cursorIndexOfTodayDeaths = CursorUtil.getColumnIndexOrThrow(_cursor, "todayDeaths");
      final int _cursorIndexOfWebserviceUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "webserviceUpdated");
      final int _cursorIndexOfPopulation = CursorUtil.getColumnIndexOrThrow(_cursor, "population");
      final int _cursorIndexOfTests = CursorUtil.getColumnIndexOrThrow(_cursor, "tests");
      final int _cursorIndexOfISOid = CursorUtil.getColumnIndexOrThrow(_cursor, "ISOid");
      final int _cursorIndexOfIso2 = CursorUtil.getColumnIndexOrThrow(_cursor, "iso2");
      final int _cursorIndexOfIso3 = CursorUtil.getColumnIndexOrThrow(_cursor, "iso3");
      final int _cursorIndexOfFlagURL = CursorUtil.getColumnIndexOrThrow(_cursor, "flagURL");
      final List<Countries> _result = new ArrayList<Countries>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Countries _item;
        final String _tmpCountry;
        _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
        final int _tmpTotalCases;
        _tmpTotalCases = _cursor.getInt(_cursorIndexOfTotalCases);
        final int _tmpTotalDeaths;
        _tmpTotalDeaths = _cursor.getInt(_cursorIndexOfTotalDeaths);
        final int _tmpTotalRecovered;
        _tmpTotalRecovered = _cursor.getInt(_cursorIndexOfTotalRecovered);
        final int _tmpTotalActive;
        _tmpTotalActive = _cursor.getInt(_cursorIndexOfTotalActive);
        final int _tmpTodayCases;
        _tmpTodayCases = _cursor.getInt(_cursorIndexOfTodayCases);
        final int _tmpTodayRecovered;
        _tmpTodayRecovered = _cursor.getInt(_cursorIndexOfTodayRecovered);
        final int _tmpTodayDeaths;
        _tmpTodayDeaths = _cursor.getInt(_cursorIndexOfTodayDeaths);
        final long _tmpWebserviceUpdated;
        _tmpWebserviceUpdated = _cursor.getLong(_cursorIndexOfWebserviceUpdated);
        final long _tmpPopulation;
        _tmpPopulation = _cursor.getLong(_cursorIndexOfPopulation);
        final long _tmpTests;
        _tmpTests = _cursor.getLong(_cursorIndexOfTests);
        final int _tmpISOid;
        _tmpISOid = _cursor.getInt(_cursorIndexOfISOid);
        final String _tmpIso2;
        _tmpIso2 = _cursor.getString(_cursorIndexOfIso2);
        final String _tmpIso3;
        _tmpIso3 = _cursor.getString(_cursorIndexOfIso3);
        final String _tmpFlagURL;
        _tmpFlagURL = _cursor.getString(_cursorIndexOfFlagURL);
        _item = new Countries(_tmpCountry,_tmpFlagURL,_tmpISOid,_tmpIso2,_tmpIso3,_tmpTotalCases,_tmpTotalDeaths,_tmpTotalRecovered,_tmpTotalActive,_tmpTodayCases,_tmpTodayRecovered,_tmpTodayDeaths,_tmpWebserviceUpdated,_tmpPopulation,_tmpTests);
        final int _tmpID;
        _tmpID = _cursor.getInt(_cursorIndexOfID);
        _item.setID(_tmpID);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
