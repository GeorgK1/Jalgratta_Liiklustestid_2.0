package com.gksoftware.jalgrattaliiklustestid.interfaces;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {}

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";

        //options
        public static final String COLUMN_OPTION_1 = "option_1";
        public static final String COLUMN_OPTION_2 = "option_2";
        public static final String COLUMN_OPTION_3 = "option_3";
        public static final String COLUMN_OPTION_4 = "option_4";
        public static final String COLUMN_OPTION_5 = "option_5";

        public static final String COLUMN_RIGHT_ANSWER = "right_answer";

        public static final String COLUMN_EXPLANATION = "explanation";

        //categories
        public static final String COLUMN_CATEGORY = "category";


    }
}
