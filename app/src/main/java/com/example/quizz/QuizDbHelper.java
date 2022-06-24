package com.example.quizz;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quizz.db";
    private static final int DATABASE_VERSION = 3;

    public static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        addCategory(c1);
        Category c2 = new Category("Geography");
        addCategory(c2);
        Category c3 = new Category("Math");
        addCategory(c3);
    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("father of c programming language",
                "Dennis Ritchie", "Bjarne Stroustrup", "James Gosling","Rasmus Lerdorf", 1, Question.DIFFICULTY_EASY,Category.PROGRAMMING);
        addQuestion(q1);

        Question q2 = new Question("What is the full form of IBM ?",
                "Indian Beta Machine", "International Business Machine", "Integral Business Machine","Internal Beta Machine", 2, Question.DIFFICULTY_EASY,Category.PROGRAMMING);
        addQuestion(q2);

        Question q3 = new Question("What is an operating system?",
                "interface between the hardware and application programs", " collection of programs that manages hardware resources", "system service provider to the application programs","all of the mentioned", 4, Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING);
        addQuestion(q3);

        Question q4 = new Question(" A _______ is the physical path over which a message travels.",
                "Path", "Medium", "Protocol","Route", 2, Question.DIFFICULTY_EASY,Category.PROGRAMMING);
        addQuestion(q4);

        Question q5 = new Question("What is the full form of DBMS?",
                "Data of Binary Management System", "Database Management System", "Database Management Service","Data Backup Management System", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        addQuestion(q5);

        Question q6 = new Question("Which of the following error can a compiler check?",
                "Syntax Error", "Logical Error", "Both Logical and Syntax Error"," Compiler cannot check errors", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q6);

        Question q7 = new Question("Which of the following computer language is written in binary codes only?",
                "pascal", "machine language", "C","C#", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q7);

        Question q8 = new Question("Which of the following is a type of computer architecture?",
                "Microarchitecture", "Harvard Architecture", " Von-Neumann Architecture","All of the mentioned", 4,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        addQuestion(q8);

        Question q9 = new Question("Who is the father of Software Engineering?",
                "Margaret Hamilton", "Watts S. Humphrey", "Alan Turing","Boris Beizer", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q9);

        Question q10 = new Question("What is the best case and worst case complexity of ordered linear search?",
                " O(nlogn), O(logn)", "O(logn), O(nlogn)", "O(1), O(n)","O(n), O(1)", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q10);

        Question q11 = new Question("Dijkstra’s Algorithm is used to solve _____________ problems.",
                " All pair shortest path", " Single source shortest path", "Network flow","Sorting", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q11);

        Question q12 = new Question("Which of the following are transport layer protocols used in networking?",
                " TCP and FTP", " UDP and HTTP", "TCP and UDP"," HTTP and FTP", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        addQuestion(q12);

        Question q13 = new Question("The landmass of which of the following continents is the least?",
                " Africa", " Asia", "Australia"," Europe", 3,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        addQuestion(q13);

        Question q14 = new Question("The Capital of USA?",
                "New York", "Miami", "Las Vegas","Washington D.C", 4,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        addQuestion(q14);

        Question q15 = new Question("The largest country in the world?",
                "Russia", "Canada", "USA","China", 1,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        addQuestion(q15);

        Question q16 = new Question("Most spoken language in the world?",
                "Mandarin chinese", "English", "Hindi","Spanish", 2,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        addQuestion(q16);

        Question q17 = new Question("Largest desert in the world?",
                "Antarctic Desert", "Arctic Desert", "Sahara Desert","Great Australian", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q17);

        Question q18 = new Question("Smallest country int the world?",
                "Nauru", "Tuvalu", "Vatican City","Palau", 3,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q18);

        Question q19 = new Question("The biggest island in the world?",
                "Madagascar", "New Guinea", "Greenland","Sumatra", 3,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q19);

        Question q20 = new Question("The longest river in the world?",
                "Nile", "Amazon", "Mississippi","Yangtze", 2,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q20);

        Question q21 = new Question("The Total number of time zones in China?",
                "1", "9", "4","3", 1,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        addQuestion(q21);

        Question q22 = new Question("Which of the following forms outermost solid part of the earth?",
                "Core", "Mantle", " Magma","Crust", 4,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        addQuestion(q22);

        Question q23 = new Question("Which among the following Volcanoes is not an active volcano?",
                "Mt. Vesuvius", "Mt. Etna", "Mt. Kilimanjaro","Mauna Kea", 3,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        addQuestion(q23);

        Question q24 = new Question("What percent of the total surface area of the Earth does the tropical rainforest biome cover?",
                "25%", "50%", "2%","10%", 3,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        addQuestion(q24);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }
    @SuppressLint("Range")//to Note
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);


        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    @SuppressLint("Range")//to Note
    public ArrayList<Question> getQuestions(int categoryID,String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();


        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
