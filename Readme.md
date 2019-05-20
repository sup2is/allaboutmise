



# about

공공데이터포털 <https://www.data.go.kr/> 에서 한국환경공단이 제공하는 대기오염정보를 사용한 미세먼지, 오존, 통합대기질정보등을 1시간단위로 표현하는 allaboutmise 입니다. (데이터 변환 시간을 조금 더 줄이고싶었으나 airkorea 에서 데이터가 바뀌는 주기가 1시간이라고 했습니다.)





# skill

spring boot + vue 로 작성한 프로그램입니다. 주된 기술은 encache lib를 사용했다는 점입니다. 이 프로그램은 db가 없이 실시간으로 사용자의 요청에 따라 즉각적으로 api를 요청하도록 작성되었습니다.



# etc

사실 원래 구성은 spring batch server + spring boot + vue 였습니다.

일단 batch 서버에서 어느정도 주기로 데이터를 긁어모아서 db에 저장하고 사용자가 페이지에 접속할때 db에 있는 가장 최신데이터를 spring boot server 에서 반환하게 하는게 목표였습니다.

근데 구현방법에 대해서 생각하는 도중 의구심이 들었던게 만약 미세먼지의 데이터 주기가 1시간인데 사용자의 브라우저는 2시간동안 새로고침 되지 않았다면? 아무리 생각해도 spring server에서 사용자의 브라우저를 강제적으로 새로고침? 시키지 못할꺼라고 생각했습니다.(물론 저의 생각)

따라서 사용자 기반으로 프로그램을 작성해야겠다고 생각하고 프로그램이 reload 될 시간을 각각의 사용자가 가질 수 있도록 작성했습니다. 그게 바로 상단에 있는 progress bar입니다.

progress bar는 한시간 단위로 사용자의 브라우저를 새로고침... 하지는 않고 axio를 사용해서 지정한 reload time이 되면 spring server와 통신하여 api를 새로 요청합니다.

그리고 조금 더 생각하는 도중 .. 과연 db가 필요할까? 라는생각을 했습니다. 어차피 실시간으로 spring server -> api server로 데이터를 요청할텐데.. 라고 생각해서 과감하게 db를 빼버리고 cache lib를 도입했습니다. 아무리 실시간이라고 해도 모든 사용자의 요청을 실시간으로 계속 요청할 수는 없으니까요.

제가 도입한 encache lib는 key값을 통하여 지정된 expiration time에 따라 데이터가 소멸되는것을 지정할 수 있습니다.

첫번째 사용자가 1시 15분에 요청한 데이터가 2시 15분까지 변경될 이유가 없었습니다(물론 작은 오차가 있을수도 있지만 배제..) 왜냐하면 airkorea 의 데이터는 1시간단위로만 변경이 되니까요

그래서 다음과같이 최초 요청한 1시 15분 데이터를 key값 기반으로 저장하고 두번째 이상 사용자부터는 같은 key값의 미리 cache 된 데이터를 보여주고 reload time을 물론 동기화 시켜야 하기 때문에 해당 key값의 expiration time을 동시에 반환하게 되었습니다

1시간은 3600 초로 이루어져있는데 만약 최초 데이터를 요청한 사람의 reload time은 0부터 시작되지만 30분 이후에 데이터를 요청한사람은 reload time이 3600의 절반인 1800초와 미리 cache된 데이터를 받게 됩니다.

아래는 시현입니다



![allaboutmise](https://user-images.githubusercontent.com/30790184/57963499-e574c580-795f-11e9-8d10-2b5b9e22e2bf.gif)







감사합니다.

