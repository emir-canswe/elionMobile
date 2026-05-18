package com.elion.assistant.data.local.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.elion.assistant.data.local.database.Converters;
import com.elion.assistant.data.local.database.entity.DailyStatEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DailyStatDao_Impl implements DailyStatDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DailyStatEntity> __insertionAdapterOfDailyStatEntity;

  private final Converters __converters = new Converters();

  public DailyStatDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDailyStatEntity = new EntityInsertionAdapter<DailyStatEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `daily_stats` (`date`,`totalTasks`,`completedTasks`,`postponedTasks`,`streakDay`,`morningBriefingShown`,`eveningAnalysisShown`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyStatEntity entity) {
        final String _tmp = __converters.toLocalDate(entity.getDate());
        if (_tmp == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, _tmp);
        }
        statement.bindLong(2, entity.getTotalTasks());
        statement.bindLong(3, entity.getCompletedTasks());
        statement.bindLong(4, entity.getPostponedTasks());
        statement.bindLong(5, entity.getStreakDay());
        final int _tmp_1 = entity.getMorningBriefingShown() ? 1 : 0;
        statement.bindLong(6, _tmp_1);
        final int _tmp_2 = entity.getEveningAnalysisShown() ? 1 : 0;
        statement.bindLong(7, _tmp_2);
      }
    };
  }

  @Override
  public Object insertOrUpdate(final DailyStatEntity stat,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDailyStatEntity.insert(stat);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DailyStatEntity>> getStatsFrom(final LocalDate startDate) {
    final String _sql = "SELECT * FROM daily_stats WHERE date >= ? ORDER BY date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.toLocalDate(startDate);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"daily_stats"}, new Callable<List<DailyStatEntity>>() {
      @Override
      @NonNull
      public List<DailyStatEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalTasks");
          final int _cursorIndexOfCompletedTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedTasks");
          final int _cursorIndexOfPostponedTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "postponedTasks");
          final int _cursorIndexOfStreakDay = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDay");
          final int _cursorIndexOfMorningBriefingShown = CursorUtil.getColumnIndexOrThrow(_cursor, "morningBriefingShown");
          final int _cursorIndexOfEveningAnalysisShown = CursorUtil.getColumnIndexOrThrow(_cursor, "eveningAnalysisShown");
          final List<DailyStatEntity> _result = new ArrayList<DailyStatEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyStatEntity _item;
            final LocalDate _tmpDate;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDate);
            }
            final LocalDate _tmp_2 = __converters.fromLocalDate(_tmp_1);
            if (_tmp_2 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpDate = _tmp_2;
            }
            final int _tmpTotalTasks;
            _tmpTotalTasks = _cursor.getInt(_cursorIndexOfTotalTasks);
            final int _tmpCompletedTasks;
            _tmpCompletedTasks = _cursor.getInt(_cursorIndexOfCompletedTasks);
            final int _tmpPostponedTasks;
            _tmpPostponedTasks = _cursor.getInt(_cursorIndexOfPostponedTasks);
            final int _tmpStreakDay;
            _tmpStreakDay = _cursor.getInt(_cursorIndexOfStreakDay);
            final boolean _tmpMorningBriefingShown;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfMorningBriefingShown);
            _tmpMorningBriefingShown = _tmp_3 != 0;
            final boolean _tmpEveningAnalysisShown;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfEveningAnalysisShown);
            _tmpEveningAnalysisShown = _tmp_4 != 0;
            _item = new DailyStatEntity(_tmpDate,_tmpTotalTasks,_tmpCompletedTasks,_tmpPostponedTasks,_tmpStreakDay,_tmpMorningBriefingShown,_tmpEveningAnalysisShown);
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
  public Object getStatForDate(final LocalDate date,
      final Continuation<? super DailyStatEntity> $completion) {
    final String _sql = "SELECT * FROM daily_stats WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.toLocalDate(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DailyStatEntity>() {
      @Override
      @Nullable
      public DailyStatEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalTasks");
          final int _cursorIndexOfCompletedTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedTasks");
          final int _cursorIndexOfPostponedTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "postponedTasks");
          final int _cursorIndexOfStreakDay = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDay");
          final int _cursorIndexOfMorningBriefingShown = CursorUtil.getColumnIndexOrThrow(_cursor, "morningBriefingShown");
          final int _cursorIndexOfEveningAnalysisShown = CursorUtil.getColumnIndexOrThrow(_cursor, "eveningAnalysisShown");
          final DailyStatEntity _result;
          if (_cursor.moveToFirst()) {
            final LocalDate _tmpDate;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDate);
            }
            final LocalDate _tmp_2 = __converters.fromLocalDate(_tmp_1);
            if (_tmp_2 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpDate = _tmp_2;
            }
            final int _tmpTotalTasks;
            _tmpTotalTasks = _cursor.getInt(_cursorIndexOfTotalTasks);
            final int _tmpCompletedTasks;
            _tmpCompletedTasks = _cursor.getInt(_cursorIndexOfCompletedTasks);
            final int _tmpPostponedTasks;
            _tmpPostponedTasks = _cursor.getInt(_cursorIndexOfPostponedTasks);
            final int _tmpStreakDay;
            _tmpStreakDay = _cursor.getInt(_cursorIndexOfStreakDay);
            final boolean _tmpMorningBriefingShown;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfMorningBriefingShown);
            _tmpMorningBriefingShown = _tmp_3 != 0;
            final boolean _tmpEveningAnalysisShown;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfEveningAnalysisShown);
            _tmpEveningAnalysisShown = _tmp_4 != 0;
            _result = new DailyStatEntity(_tmpDate,_tmpTotalTasks,_tmpCompletedTasks,_tmpPostponedTasks,_tmpStreakDay,_tmpMorningBriefingShown,_tmpEveningAnalysisShown);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getRecentStats(final int limit,
      final Continuation<? super List<DailyStatEntity>> $completion) {
    final String _sql = "SELECT * FROM daily_stats ORDER BY date DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyStatEntity>>() {
      @Override
      @NonNull
      public List<DailyStatEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "totalTasks");
          final int _cursorIndexOfCompletedTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "completedTasks");
          final int _cursorIndexOfPostponedTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "postponedTasks");
          final int _cursorIndexOfStreakDay = CursorUtil.getColumnIndexOrThrow(_cursor, "streakDay");
          final int _cursorIndexOfMorningBriefingShown = CursorUtil.getColumnIndexOrThrow(_cursor, "morningBriefingShown");
          final int _cursorIndexOfEveningAnalysisShown = CursorUtil.getColumnIndexOrThrow(_cursor, "eveningAnalysisShown");
          final List<DailyStatEntity> _result = new ArrayList<DailyStatEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyStatEntity _item;
            final LocalDate _tmpDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfDate);
            }
            final LocalDate _tmp_1 = __converters.fromLocalDate(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpDate = _tmp_1;
            }
            final int _tmpTotalTasks;
            _tmpTotalTasks = _cursor.getInt(_cursorIndexOfTotalTasks);
            final int _tmpCompletedTasks;
            _tmpCompletedTasks = _cursor.getInt(_cursorIndexOfCompletedTasks);
            final int _tmpPostponedTasks;
            _tmpPostponedTasks = _cursor.getInt(_cursorIndexOfPostponedTasks);
            final int _tmpStreakDay;
            _tmpStreakDay = _cursor.getInt(_cursorIndexOfStreakDay);
            final boolean _tmpMorningBriefingShown;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfMorningBriefingShown);
            _tmpMorningBriefingShown = _tmp_2 != 0;
            final boolean _tmpEveningAnalysisShown;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfEveningAnalysisShown);
            _tmpEveningAnalysisShown = _tmp_3 != 0;
            _item = new DailyStatEntity(_tmpDate,_tmpTotalTasks,_tmpCompletedTasks,_tmpPostponedTasks,_tmpStreakDay,_tmpMorningBriefingShown,_tmpEveningAnalysisShown);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
