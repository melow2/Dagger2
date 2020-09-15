# Dagger2
## What is "Dependency Injection"?
아주 간단한 예로 Car라는 클래스가 있는데 ,Car라는 클래스는 Engine이라는 클래스의 지원없이 사용할 수 없다고 하면,이것은 Car는 Engine에 의존한다는 것을 의미한다.
엔진을 재사용하지 않고서는 자동차를 재사용할 수 없고, 그래서 Engine클래스는 Car클래스의 dependency라고 간주할 수 있다.
그러나 엔진은 자동차에 의종하지 않을 수도 있다.

엔진 클래스를 살펴본다면, Piston이라는 종속성을 갖고 있다. 엔진은 Piston이 없이는 살아남을 수 없으며, 엔진은 dependent(종속물)이 되고, Piston은 dependency(종속성)이 된다.
그러면 이제 Car Object에 대해서 뭐라고 말할 수 있을까? 우리는 방금 Piston이라는 간접적인 의존성을 찾았다.

실제 대규모 프로젝트에서는 많은 의존성을 찾을 수 있을 것이고, 우리는 그것들 중 일부를 만들 수도 있고, 제3자 의존성을 사용할 수도 있다.
```
REST API와 통신하기 위해 Retrofit를 사용하는 경우, Retrofit 인스턴스는 종속성이 된다.
룸을 사용하려는 경우, 룸 데이터 베이스 인스턴스는 종속성이 된다.
```
**의존성이 긴밀하게 결합되어 있다면, 테스트, 버그 수정, 그리고 코드의 확장을 하는 것은 매우 어렵다.**
우리는 느슨하게 결합되고 높은 응집력 의존성을 받아들여야 한다. 그것이 우리가 Dependency Injection을 하는 이유다. 
산업 수준의 대규모 프로젝트에서 우리는 프로젝트의 후반 단계에서 발생할 수 있는 불필요한 문제를 방지하기 위해 의존성 주입 아키텍처 패턴을 따르도록 주의를 기울여야 한다.
Object Oriented Software Engineering의 5번째 원칙은 의존성 뒤집기 원칙(dependency Inversion Principle)이다.

이 원칙은, Entities는 착상이 아닌 추상화(abstractions)에 의존해야 한다고 말한다.

#
## Pure Dependency Injection 
Battery와 SIMCard, MemoryCard 모두 dependency injection이 되었고, 이러한 유형의 dependency injection을 Cconstructor injection이라고 부른다.
이때 ServiceProvider는 indirect dependency injection이 된 상태다. 이 외에도 setFunction()을 통한 method injection 등이 있다. 
```
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        val smartPhone = SmartPhone(
            Battery(),
            SIMCard(ServiceProvider()),
            MemoryCard()
        ).makeACallWithRecording()
        // Dependency Injection
        
    }
}
```
#
## Constructor Injection With Dagger 2
###
```
class SmartPhone @Inject constructor(val battery: Battery, val simCard: SIMCard, val memoryCard: MemoryCard) {

    init {
        battery.getPower()
        simCard.getConnection()
        memoryCard.getSpaceAvailablity()
        Log.i("MYTAG", "SmartPhone Constructed")
    }

    fun makeACallWithRecording() {
        Log.i("MYTAG", "Calling.....")
    }
}

class Battery @Inject constructor() {
    init {
        Log.i("MYTAG","Battery Constructed")
    }

    fun getPower(){
        Log.i("MYTAG","Battery power is available")
    }
}

class MemoryCard @Inject constructor() {
    init {
        Log.i("MYTAG","Memory Card Constructed")
    }

    fun getSpaceAvailablity(){
        Log.i("MYTAG","Memory space available")
    }
}

class SIMCard @Inject constructor(private val serviceProvider: ServiceProvider) {


    init {
        Log.i("MYTAG","SIM Card Constructed")
    }

    fun getConnection(){
        serviceProvider.getServiceProvider()
    }
}

class ServiceProvider @Inject constructor() {
    init {
        Log.i("MYTAG","Service Provider Constructed")
    }

    fun getServiceProvider(){
        Log.i("MYTAG","Service provider connected")
    }
}

@Component
interface SmartPhoneComponent {
    fun getSmartPhone() : SmartPhone
}
```
```
DaggerSmartPhoneComponent.create()
    .getSmartPhone()
    .makeACallWithRecording()
```
