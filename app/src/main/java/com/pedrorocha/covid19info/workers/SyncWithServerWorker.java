package com.pedrorocha.covid19info.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.pedrorocha.covid19info.utils.AppConstants.WORK_MANAGER_KEYS;

public class SyncWithServerWorker extends Worker {

    public SyncWithServerWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String ISO2 = getInputData().getString(WORK_MANAGER_KEYS.SYNC_ISO2);
        String slug = getInputData().getString(WORK_MANAGER_KEYS.SYNC_SLUG);

        /*
        * Downloads server data, store it and notify user new data is available?
        * */

        return Result.success();
    }
}
