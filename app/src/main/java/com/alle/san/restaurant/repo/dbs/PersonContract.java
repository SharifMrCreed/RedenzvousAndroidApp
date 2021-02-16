package com.alle.san.restaurant.repo.dbs;

import android.provider.BaseColumns;

public final class PersonContract {

    private PersonContract() {}

    public static final class PersonTable implements BaseColumns {

        public static final String TABLE_NAME = "Users";
        public static final String ID = BaseColumns._ID;
        public static final String FULL_NAME = "fullnames";
        public static final String EMAIL = "emailadress";
        public static final String PASSWORD = "password";
    }

}
