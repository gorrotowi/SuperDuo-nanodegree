package barqsoft.footballscores;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import barqsoft.footballscores.DatabaseContract.scores_table;

/**
 * Created by yehya khaled on 2/25/2015.
 */
public class ScoresDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Scores.db";
    private static final int DATABASE_VERSION = 2;
    private Context context;

    public ScoresDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CreateScoresTable = context.getString(R.string.create_trable_query) + DatabaseContract.SCORES_TABLE + context.getString(R.string.query_parent)
                + scores_table._ID + context.getString(R.string.integer_primary_key_query)
                + scores_table.DATE_COL + context.getString(R.string.text_not_null_query)
                + scores_table.TIME_COL + context.getString(R.string.integer_not_null_query)
                + scores_table.HOME_COL + context.getString(R.string.text_not_null_query)
                + scores_table.AWAY_COL + context.getString(R.string.text_not_null_query)
                + scores_table.LEAGUE_COL + context.getString(R.string.integer_not_null_query)
                + scores_table.HOME_GOALS_COL + context.getString(R.string.text_not_null_query)
                + scores_table.AWAY_GOALS_COL + context.getString(R.string.text_not_null_query)
                + scores_table.MATCH_ID + context.getString(R.string.integer_not_null_query)
                + scores_table.MATCH_DAY + context.getString(R.string.integer_not_null_query)
                + context.getString(R.string.unique_query) + scores_table.MATCH_ID + context.getString(R.string.on_conflict_query)
                + context.getString(R.string.end_query);
        db.execSQL(CreateScoresTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Remove old values when upgrading.
        db.execSQL(context.getString(R.string.drop_table_query) + DatabaseContract.SCORES_TABLE);
    }
}
