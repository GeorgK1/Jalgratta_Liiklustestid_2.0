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
        public static final String COLUMN_CATEGORY= "category";

        public static final String COLUMN_CATEGORY_TEEANDMISE_KOHUSTUS = "category";
        public static final String COLUMN_CATEGORY_SÕIDUKI_ASUKOHT_SÕITMISEL = "category";
        public static final String COLUMN_CATEGORY_VASAK_JA_TAGASIPÖÖRE = "category";
        public static final String COLUMN_CATEGORY_ÜMBERPÕIGE = "category";
        public static final String COLUMN_CATEGORY_SÕITMINE_RISTMIKEL = "category";
        public static final String COLUMN_CATEGORY_SÕIDUTEE_ÜLETAMINE_ÜLEKÄIGUKOHA_VÕI_ÜLEKÄIGURAJA_KAUDU = "category";
        public static final String COLUMN_CATEGORY_JUHI_KOHUSTUSED_JALAKÄIJATE_OHUTUSE_TAGAMISEL = "category";
        public static final String COLUMN_CATEGORY_SÕITMINE_JALGRATTATEEL_VÕI_JALGRATTA_JA_JALGTEEL = "category";
        public static final String COLUMN_CATEGORY_SÕITMINE_PIMEDA_AJAL_VÕI_HALVA_NÄHTAVUSE_TINGIMUSTES = "category";
        public static final String COLUMN_CATEGORY_JALGRATTURI_JA_PISIMOPEEDIJUHI_OHUTUS_JA_KÄITUMINE_LIIKLUSES = "category";


    }
}
