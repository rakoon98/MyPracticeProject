package com.example.test.ui.hilt

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

interface Car {
    fun getOilType() : OilType
}

enum class OilType {
    GASOLINE,
    DIESEL,
    LPG
}

class GasolineCarC : Car {
    override fun getOilType(): OilType = OilType.GASOLINE

    // 필요한 스코프를 생각해서 InstallIn 지정
    @InstallIn(SingletonComponent::class)
    // AndroidEntryPoint 지정이 안되는 Hilt 지정 불가능 class에서 주입받을수있게하는것.
    @EntryPoint
    interface getData {
        fun getString() : String
    }


//    //필요한 스코프를 생각해서 InstallIn을 정한다.
//    @InstallIn(ApplicationComponent::class) --> SingletonComponent::class 로 변경됨
//    @EntryPoint // 이 녀석으로 hilt가 entryPoint임을 알 수 있게 된다.
//    interface DbEntryPoint{
//        fun database():AppDatabase
//    }
//
//    private fun getDb(context: Context):AppDatabase{
//        //EntryPointAccessors:static accessor
//        //다음의 파라미터들은 hilt-component가 알 수 있는 값들이어야 한다.
//        //context는 디폴트로 가지고 있고, DbEntryPoint는 위에서 지정해 주었다.
//        return EntryPointAccessors.fromApplication(
//            context,
//            DbEntryPoint::class.java
//        ).database()
//    }
//
//    //그리고 사용한다.
//    fun getDestination() = getDb(context).mapDao.destination

}

class DieselCarC : Car{
    override fun getOilType(): OilType = OilType.DIESEL

}