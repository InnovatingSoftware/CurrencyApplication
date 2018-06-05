package com.app.andres.baseapplication.eventbus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {

    private static final RxBus INSTANCE = new RxBus();

    private final Subject<Object> busSubject = PublishSubject.create();

    public static RxBus getInstance() {
        return INSTANCE;
    }

    public <T> Disposable register(final Class<T> eventClass, Consumer<T> onNext) {


        return busSubject.filter(new Predicate<Object>() {
            @Override
            public boolean test(Object event) throws Exception {
                return event.getClass().equals(eventClass);
            }
        }).map(new Function<Object, T>() {
            @Override
            public T apply(Object object) throws Exception {
                return (T) object;
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(onNext);
    }

    public void unRegister(Disposable disposables) {
        if (disposables != null && disposables.isDisposed())
            disposables.dispose();
    }

    public void post(Object event) {
        busSubject.onNext(event);
    }

}
