# android_Pieooreum
설명
=============
# data 패키지
> 앱에서 사용되는 DB가 Room 으로 구현되어 있습니다. 
> 데이터를 사용하고 싶다면, Dao에 새로운 SQL문을 작성하고, 이를 Repository에 등록한뒤, ViewModel객체를 통해 참조하면 됩니다.
> > 2020-11-18 현재 Dexer, Exercise, Group, Routine 의 구현을 하는 중입니다.

# viewmodels 패키지
> 실직적으로 fragmenr/activity 의 데이터를 표시해주는 역할을 하고 있습니다.
> 데이타를 표시해여 하는 UI를 작성하고 싶다면 이 viewModel 클래스를 통해 객체를 생성하고, 함수를 호출하면 됩니다. 
> > 2020-11-18 Dexer,Group,Routine 의 데이타를 필요로 하는 UI를 위해 viewModel의 구현이 필요합니다.

# com.smu.team.andeu 패키지
> UI를 표시할 수있는 Activity, Fragment를 작성하는 곳입니다.
> > 2020-11-18 MainActivity, ExerActivity, ExerFragment, ExerListFragment 가 구현되어 있습니다. 

# workers 패키지
> DB에 기초 base로 들어가야 하는 데이타를 넣어주는 작업을 하는 Worker 클래스 들이 있습니다.
> > 2020-11-18 현재 AppDatabase 클래스에서 싱글턴 으로 객체를 만들때, json으로 작성된 데이타를 넣어주는 일을 하는 BaseDatabaseWorker가 작성되어 있습니다.

# callback 패키지
> 콜백의 인터페이스가 있습니다. 콜백 클래스는 이 패키지 아래에 작성하면 됩니다.
> > 2020-11-18 현재 운동들의 리스트가 표현되는 ExerListFragment에서 Exer 하나를 클릭하면, 이 콜백으로 연결되어 있습니다. 

# adapters 패키지
> RecyclerView 에서 사용하는 어뎁터를 정의하는 곳입니다. 여러데이터를 List 형태나 등등의 형태로 보여주고 싶을때, 어뎁터를 이용하면 좋습니다.
> > 2020-11-18 ExerListAdapter 를 통해 운동들의 리스트를 담당하고 있습니다.
> > 2020-11-18 BindingAdapters 를 통해 DataBinding 이라는 기술을 담당하고 있습니다. 

# assert 폴더
> json 형태로, 기초 데이타를 모아 놓은 곳입니다. 
> > 2020-11-18 현재 Exercise의 데이타만 있습니다.

# 감사합니다.
