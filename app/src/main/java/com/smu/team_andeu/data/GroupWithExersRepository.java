package com.smu.team_andeu.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GroupWithExersRepository {

    private static GroupWithExersRepository sInstance;
    private final GroupWithExersDao groupWithExersDao;

    public GroupWithExersRepository(final Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        groupWithExersDao = db.groupWithExersDao();
    }

    public static GroupWithExersRepository getInstance(final Application application) {
        if (sInstance == null) {
            synchronized (ExerciseRepository.class) {
                if (sInstance == null) {
                    sInstance = new GroupWithExersRepository(application);
                }
            }
        }
        return sInstance;
    }

    // 모든 그룹들을 다 반환합니다.
    public LiveData<List<GroupWithExers>> getAllGroups() {
        return groupWithExersDao.getGroupsWithExers();
    }

    // 해당하는 그룹을 반환합니다.
    public LiveData<List<GroupWithExers>> getGroupById(int gId) {
        return groupWithExersDao.getGroupWithExers(gId);
    }

}
