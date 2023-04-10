//package com.example.test.di
//
//import com.example.test.ui.hilt.Car
//import com.example.test.ui.hilt.DieselCarC
//import com.example.test.ui.hilt.GasolineCarC
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ActivityComponent
//import javax.inject.Qualifier
//
//
//@InstallIn(ActivityComponent::class)
//@Module
//abstract class CarModule {
//
//    @GasolineCar
//    @Binds
//    abstract fun bindGasolineCar(impl : GasolineCarC) : Car
//
//    @DieselCar
//    @Binds
//    abstract fun bindDieselCar(impl : DieselCarC) : Car
//
//    @Qualifier
//    annotation class GasolineCar
//
//    @Qualifier
//    annotation class DieselCar
//
//}