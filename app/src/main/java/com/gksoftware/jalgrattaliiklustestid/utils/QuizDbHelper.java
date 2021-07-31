package com.gksoftware.jalgrattaliiklustestid.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.gksoftware.jalgrattaliiklustestid.interfaces.QuizContract;
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
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_CATEGORY + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION_1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION_2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION_3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION_4 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION_5 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_RIGHT_ANSWER + " INTEGER, " +
                QuizContract.QuestionsTable.COLUMN_EXPLANATION + " TEXT" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
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
                "Jalgrattur B", "puudub", "puudub", "puudub", 1, "teeandmise_kohustus",
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q6);

        Question q7 = new Question("Kes peab andma teed7?", "Jalgrattur A",
                "Jalgrattur B", "puudub", "puudub", "puudub", 1, "teeandmise_kohustus",
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q7);

        Question q8 = new Question("Kes peab andma teed8?", "Jalgrattur A",
                "Jalgrattur B", "puudub", "puudub", "puudub", 1, "teeandmise_kohustus",
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q8);

        Question q9 = new Question("Kes peab andma teed9?", "Jalgrattur A",
                "Jalgrattur B", "puudub", "puudub", "puudub", 1, "teeandmise_kohustus",
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q9);

        Question q10 = new Question("Kes peab andma teed10?", "Jalgrattur A",
                "Jalgrattur B", "puudub", "puudub", "puudub", 1, "teeandmise_kohustus",
                "Jalgrattur B peab andma teed, kuna temal põleb fooris punane tuli, mis keelab edasi liikuda.");
        addQuestion(q10);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION_1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION_2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION_3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION_4, question.getOption4());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION_5, question.getOption5());
        cv.put(QuizContract.QuestionsTable.COLUMN_RIGHT_ANSWER, question.getRightAnswer());
        cv.put(QuizContract.QuestionsTable.COLUMN_CATEGORY, question.getCategory());
        cv.put(QuizContract.QuestionsTable.COLUMN_EXPLANATION, question.getExplanation());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION_1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION_2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION_3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION_4)));
                question.setOption5(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION_5)));
                question.setRightAnswer(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_RIGHT_ANSWER)));
                question.setCategory(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_CATEGORY)));
                question.setExplanation(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_EXPLANATION)));
                questionList.add(question);
            } while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
