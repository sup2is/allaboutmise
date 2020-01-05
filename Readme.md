# How to start?



AWS EC2 free tier를 사용했습니다

http://ec2-54-180-112-123.ap-northeast-2.compute.amazonaws.com:8081/#



## 1. QuickStart

- git clone https://github.com/sup2is/allaboutmise.git
- cd /allaboutmise
- java -jar allaboutmise-0.0.1-SNAPSHOT.jar (require java 8 ↑)
- http://localhost:8081 접속





## 2. IDE Start

- git clone https://github.com/sup2is/allaboutmise.git
- cd /allaboutmise/frontend/
- npm install (require npm)
- npm run build 
- SpringBootApplication start (require java 8 ↑)

* http://localhost:8081 접속



# About

공공데이터포털 <https://www.data.go.kr/> 에서 한국환경공단이 제공하는 대기오염정보를 사용한 미세먼지, 오존, 통합대기질정보등을 1시간단위로 표현하는 allaboutmise 입니다. (데이터 변환 시간을 조금 더 줄이고싶었으나 airkorea 에서 데이터가 바뀌는 주기가 1시간인점 양해 바랍니다)





# Skill



Spring boot + Vue 로 작성한 프로그램입니다.

Spring boot와 Vue를 통합시켜 front와 back단을 완전히 분리시킨 뒤 둘 사이의 요청은 반드시 api요청으로 이루어지도록 만들었습니다. 실시간 데이터만 표출하기때문에 과감하게 DB를 버렸으나 추후 JPA를 사용한 data history를 생성할 예정입니다.



# Shortcut



![allaboutmise](https://user-images.githubusercontent.com/30790184/57963499-e574c580-795f-11e9-8d10-2b5b9e22e2bf.gif)



감사합니다.