package com.example.covid19appretrotest.database;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.URI;

public class ContentProvider extends android.content.ContentProvider {
    public static final String TAG = ContentProvider.class.getName();

    private ZoneDao mZoneDao;

    public static final String AUTHORITY = "com.example.covid19appretrotest.database.provider";
    public static final String ZONE_TABLE_NAME = "zone_stats_table";

    /** The match code for SOME items in the zone table. */
    private static final int CODE_ZONE_DIR = 1;

    /** The match code for AN item in the zone table. */
    private static final int CODE_ZONE_ITEM = 2;

    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, ZONE_TABLE_NAME, CODE_ZONE_DIR);
        uriMatcher.addURI(AUTHORITY, ZONE_TABLE_NAME + "/*", CODE_ZONE_ITEM);
    }


    @Override
    public boolean onCreate() {
        mZoneDao = CovidDatabase.getInstance(getContext()).mZoneDao();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Log.d(TAG, "query");

        final int code = uriMatcher.match(uri);
        final Cursor cursor;

        if (code == CODE_ZONE_DIR) {
            cursor = mZoneDao.selectAll();

            final Context context = getContext();
            if (context == null) {
                return null;
            }

            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;

        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        switch (uriMatcher.match(uri)) {
            case CODE_ZONE_DIR:
                final Context context = getContext();
                if (context == null) {
                    return null;
                }
                Zone zone = Zone.fromContentValues(contentValues);
                mZoneDao.insert(zone);
                final String id = zone.getZoneName();

                context.getContentResolver().notifyChange(uri, null);

                return UriCombine(uri, id);

            case CODE_ZONE_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        switch (uriMatcher.match(uri)) {
            case CODE_ZONE_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);

            case CODE_ZONE_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                String zoneName = uri.getLastPathSegment();

                Zone zone = mZoneDao.getZone(zoneName);
                int count = mZoneDao.delete(zone);

                context.getContentResolver().notifyChange(uri, null);
                return count;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        switch (uriMatcher.match(uri)) {
            case CODE_ZONE_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_ZONE_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }

                Zone zone = Zone.fromContentValues(contentValues);

                int count = mZoneDao.update(zone);

                context.getContentResolver().notifyChange(uri, null);
                return count;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    public static Uri UriCombine(Uri uri, String add)
    {
        String str = uri.toString();

        if (!str.endsWith("/"))
        {
            str = str + "/";
        }
        String out = str + add;
        Uri newUri = Uri.parse(str);

        return newUri;
    }
}
