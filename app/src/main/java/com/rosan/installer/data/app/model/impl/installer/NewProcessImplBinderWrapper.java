package com.rosan.installer.data.app.model.impl.installer;


import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ShellCallback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rosan.app_process.NewProcessImpl;

import java.io.FileDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class NewProcessImplBinderWrapper implements IBinder {
    private final NewProcessImpl mNewProcessImpl;

    private final IBinder mBinder;

    NewProcessImplBinderWrapper(@NonNull NewProcessImpl newProcessImpl, @NonNull IBinder binder) {
        this.mNewProcessImpl = newProcessImpl;
        this.mBinder = binder;
    }

    @Nullable
    @Override
    public String getInterfaceDescriptor() throws RemoteException {
        return mBinder.getInterfaceDescriptor();
    }

    @Override
    public boolean pingBinder() {
        return mBinder.pingBinder();
    }

    @Override
    public boolean isBinderAlive() {
        return mBinder.isBinderAlive();
    }

    @Nullable
    @Override
    public IInterface queryLocalInterface(@NonNull String descriptor) {
        return null;
    }

    @Override
    public void dump(@NonNull FileDescriptor fd, @Nullable String[] args) throws RemoteException {
        mBinder.dump(fd, args);
    }

    @Override
    public void dumpAsync(@NonNull FileDescriptor fd, @Nullable String[] args) throws RemoteException {
        mBinder.dumpAsync(fd, args);
    }

    @Override
    public void shellCommand(FileDescriptor in, FileDescriptor out, FileDescriptor err, String[] args, ShellCallback shellCallback, ResultReceiver resultReceiver) throws RemoteException {
        mBinder.shellCommand(in, out, err, args, shellCallback, resultReceiver);
    }

    @Override
    public IBinder getExtension() throws RemoteException {
        return IBinder.super.getExtension();
    }

    @Override
    public boolean transact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        try {
            Method targetTransactMethod = NewProcessImpl.class.getDeclaredMethod("targetTransact");
            targetTransactMethod.setAccessible(true);
            return (boolean) targetTransactMethod.invoke(mNewProcessImpl, mBinder, code, data, reply, flags);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void linkToDeath(@NonNull DeathRecipient recipient, int flags) throws RemoteException {
        mBinder.linkToDeath(recipient, flags);
    }

    @Override
    public boolean unlinkToDeath(@NonNull DeathRecipient recipient, int flags) {
        return mBinder.unlinkToDeath(recipient, flags);
    }
}
