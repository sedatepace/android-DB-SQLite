# Android DB SQLite 예제

## Introduce

- 안드로이드(Android)에서 데이터를 저장하는 방법이 여러가지가 있는데 이번에는 SQLite방법으로 저장하는 방법을 소개한다. 

- 일반적으로 SQLite 사용하기 위해서는 SQLiteOpenHelper 클래스, SQLiteDatabase 클래스, Cursor 인터페이스를 사용한다.



## Method

- 이번에 시험성적을 프로그램을 만드는 예제를 통해 이름, 시험과목, 성적을 SQLite 방법을 통해서 저장해본다. 

### 기본 설정

- DB 구조(SQLite)

| 컬럼명 | 타입   | 설명|비고|
| ------- | ------ |------| ------- |
|_id|INTEGER|PRIMARY KEY|AUTOINCREMENT NOT NULL|
|name|TEXT|이름||
|subject|TEXT|과목||
|score|INTEGER|성적||






### 순서

1. 화면설정
2. SQLiteOpenHelper  클래스 만들기

```java
public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context) {
        super(context, "subjectDB", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE subjectDB ( "
                                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                                + "name TEXT, "
                                + "subject TEXT, "
                                + "score INTEGER"
                        + ");"
                    );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS subjectDB");
        onCreate(db);

    }
}

```



## Result

## Conclustion

## Reference

1. Android 공식 sqlite 예제, https://developer.android.com/training/data-storage/sqlite?hl=ko
