package com.cleanup.todoc;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.base.TodocDatabase;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class DataBaseTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TaskDao taskDao;
    private TodocDatabase db;
    private long ID_TEST = 2;
    Task TASK = new Task(ID_TEST, 2, "nettoyage", 2);

    @Before
    public void initDb() throws Exception {
        this.db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void createTask() throws Exception {
        // BEFORE : Adding a new user
        this.db.mTaskDao().createTask(TASK);
        //
        Task mTask = LiveDataTestUtil.getValue(this.db.mTaskDao().getTask(ID_TEST));
        assertTrue(mTask.getName().equals(TASK.getName()) && mTask.getId() == ID_TEST);
    }

    @Test
    public void deleteTask() throws Exception{
        this.db.mTaskDao().createTask(TASK);
        List<Task> testList = LiveDataTestUtil.getValue(db.mTaskDao().getTasks());
        assertTrue(testList.size() == 1);
        this.db.mTaskDao().deleteTask(TASK);
        testList = LiveDataTestUtil.getValue(db.mTaskDao().getTasks());
        assertTrue( testList.size() == 0);
    }

    @Test
    public void getTasks() throws Exception {
        List<Task> tasks = LiveDataTestUtil.getValue(this.db.mTaskDao().getTasks());
        assertTrue(tasks.isEmpty());
        db.mTaskDao().createTask(TASK);
        tasks = LiveDataTestUtil.getValue(this.db.mTaskDao().getTasks());
        assertFalse(tasks.size() == 0);
    }
}

