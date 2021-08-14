package com.gksoftware.jalgrattaliiklustestid.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.gksoftware.jalgrattaliiklustestid.interfaces.QuizContract.*;
import com.gksoftware.jalgrattaliiklustestid.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Liiklustestid.DB";
    private static final int DATABASE_VERSION = 3;

    private SQLiteDatabase db;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_CATEGORY + " TEXT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION_1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION_2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION_3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION_4 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION_5 + " TEXT, " +

                QuestionsTable.COLUMN_RIGHT_ANSWER + " INTEGER, " +
                QuestionsTable.COLUMN_EXPLANATION + " TEXT" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Kes peab andma teed?", "Jalgrattur A", "Jalgrattur B",
                "puudub", "puudub", "puudub", 1, "teeandmise_kohustus",
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q1);
        Question q2 = new Question("Kes peab andma teed2?", "Jalgrattur A", "Jalgrattur B",
                "puudub", "puudub", "puudub", 1, "teeandmise_kohustus",
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q2);

        Question q3 = new Question("Kes peab andma teed3?", "Jalgrattur A", "Jalgrattur B", "puudub",
                "puudub", "puudub", 1, "teeandmise_kohustus",
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q3);

        Question q4 = new Question("Kes peab andma teed4?", "Jalgrattur A",
                "Jalgrattur B", "puudub", "puudub", "puudub", 1, "teeandmise_kohustus",
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q4);

        Question q5 = new Question("Kes peab andma teed5?", "Jalgrattur A",
                "Jalgrattur B", "puudub", "puudub", "puudub", 1, "teeandmise_kohustus",
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q5);

        Question q6 = new Question("Kes peab andma teed6?", "Jalgrattur A",
                "Jalgrattur B", "puudub", "puudub", "puudub", 1, QuestionsTable.COLUMN_CATEGORY_TEEANDMISE_KOHUSTUS,
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q6);

        Question q7 = new Question("Kes peab andma teed7?", "Jalgrattur A",
                "Jalgrattur B", "puudub", "puudub", "puudub", 1, QuestionsTable.COLUMN_CATEGORY_TEEANDMISE_KOHUSTUS,
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q7);

        Question q8 = new Question("Kes peab andma teed8?", "Jalgrattur A",
                "Jalgrattur B", "puudub", "puudub", "puudub", 1, QuestionsTable.COLUMN_CATEGORY_TEEANDMISE_KOHUSTUS,
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q8);

        Question q9 = new Question("Kes peab andma teed9?", "Jalgrattur A",
                "Jalgrattur B", "puudub", "puudub", "puudub", 1, QuestionsTable.COLUMN_CATEGORY_TEEANDMISE_KOHUSTUS,
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q9);

        Question q10 = new Question("Kes peab andma teed10?", "Jalgrattur A",
                "Jalgrattur B", "puudub", "puudub", "puudub", 1, QuestionsTable.COLUMN_CATEGORY_TEEANDMISE_KOHUSTUS,
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q10);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION_1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION_2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION_3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION_4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_OPTION_5, question.getOption5());
        cv.put(QuestionsTable.COLUMN_RIGHT_ANSWER, question.getRightAnswer());
        cv.put(QuestionsTable.COLUMN_CATEGORY, question.getCategory());
        cv.put(QuestionsTable.COLUMN_EXPLANATION, question.getExplanation());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION_1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION_2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION_3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION_4)));
                question.setOption5(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION_5)));
                question.setRightAnswer(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_RIGHT_ANSWER)));
                question.setCategory(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY)));
                question.setExplanation(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_EXPLANATION)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public List<Question> getQuestionsByCategory(String category) {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String[] selectionArgs = new String[]{category};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_CATEGORY + " = ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION_1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION_2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION_3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION_4)));
                question.setOption5(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION_5)));
                question.setRightAnswer(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_RIGHT_ANSWER)));
                question.setCategory(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY)));
                question.setExplanation(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_EXPLANATION)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

}
