package com.smu.team_andeu.data;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.RewriteQueriesToDropUnusedColumns;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.smu.team_andeu.workers.BaseDexerDatabaseWorker;
import com.smu.team_andeu.workers.BaseExerDatabaseWorker;
import com.smu.team_andeu.workers.BaseGroupDatabaseWorker;
import com.smu.team_andeu.workers.BaseGroupExerCrossRefWorker;
import com.smu.team_andeu.workers.BaseRoutineDatabaseWorker;

import static com.smu.team_andeu.utilities.ConstantsKt.DATABASE_NAME;

// 추후에 데이타베이스의 스키마를 변경하게 된다면 아래 참고
// https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929

@Database(entities = {Exercise.class, ExerciseFts.class, Dexer.class, Group.class, GroupExerCrossRef.class, Routine.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DexerDao dexerDao();

    public abstract ExerciseDao exerDao();

    public abstract RoutineWithDexersDao routineWithDexersDao();

    public abstract GroupWithExersDao groupWithExersDao();

    public abstract RoutineDao routineDao();

    public abstract GroupDao groupDao();

    public abstract GroupExerCrossRefDao groupExerCrossRefDao();

    //매 번 변수의 값을 Read할 때마다 CPU cache에 저장된 값이 아닌 Main Memory에서 읽는 것입니다.
    private static volatile AppDatabase INSTANCE;

    // run database operations asynchronously on a background thread.
    // 데이타 입력시 다른 쓰레드에서 이용해야함
//    static final ExecutorService databaseWriteExecutor =
//            Executors.newFixedThreadPool(4);


    // 데이타베이스 생성 후 바로 필요한 데이타를 넣는 방법 : Pre-populate the database
    // https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1#4785
    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, DATABASE_NAME)
                .addCallback(
                        new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                // Worker를 통해 request를 만들어 WorkManager에게 위임.
                                WorkRequest exerRequest = new OneTimeWorkRequest.Builder(BaseExerDatabaseWorker.class).build();
                                WorkRequest groupRequest = new OneTimeWorkRequest.Builder(BaseGroupDatabaseWorker.class).build();
                                WorkRequest crossRequest = new OneTimeWorkRequest.Builder(BaseGroupExerCrossRefWorker.class).build();
                                WorkRequest dexerRequest = new OneTimeWorkRequest.Builder(BaseDexerDatabaseWorker.class).build();
                                WorkRequest routineRequest = new OneTimeWorkRequest.Builder(BaseRoutineDatabaseWorker.class).build();
                                // 새로운 Worker 삽입 가능.
                                WorkManager.getInstance(context).enqueue(exerRequest);
                                WorkManager.getInstance(context).enqueue(groupRequest);
                                WorkManager.getInstance(context).enqueue(crossRequest);
                                WorkManager.getInstance(context).enqueue(dexerRequest);
                                WorkManager.getInstance(context).enqueue(routineRequest);

                            }
                        }
                )
                //.addMigrations(MIGRATION_1_2)
                .build();
    }


//    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `exersFts` USING FTS4("
//                    + "`name` TEXT, `description` TEXT, content=`exers`)");
//            database.execSQL("INSERT INTO exersFts (`rowid`, `name`, `description`) "
//                    + "SELECT `id`, `name`, `description` FROM exers");
//
//        }
//    };


}
