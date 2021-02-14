package com.evangelidis.t_tnews.databases

import androidx.room.Room
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    single { Room.databaseBuilder(get(), NewsRoomDatabase::class.java, "app.db").build() }

    single { get<NewsRoomDatabase>().articleDao() }

    single<IArticleRepository> {
        ArticleRepository(get())
    }

    single<IArticleInteractor> {
        ArticleInteractor(get())
    }

    viewModel { ArticlePresenter(get()) }
}
