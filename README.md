<a align="center" href="https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat
"><img alt="API" src="https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat"/></a>
<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  
<h1 align="center">Covid-19 Worldwide Info</h1>


<p align="center">Android app to provide information about Covid19. The project aims to be useful to the community and study Android.</p>

<div>
  <img src="/images/main.jpeg" width="40%"/>
  <img src="/images/info.jpeg" align="right" width="40%"/>
</div>


### Download

App can't be uploaded to the Google Play Store due to policy issues.


_Pursuant to Section 8.3 of the Developer Distribution Agreement and the Enforcement policy, apps referencing COVID-19, or related terms, in any form will only be approved for distribution on Google Play if they are published, commissioned, or authorized by official government entities or public health organizations. Google reserves the discretion to remove apps from Google Play based on a number of factors including a high risk of abuse._


To download the app, you must go to the [releases](https://github.com/Pedr0Rocha/Covid19-Info/releases) page and download the debug APK.


### Tech stack:
- [x] MVVM
- [x] LiveData
- [x] Transformations
- [x] DataBinding
- [x] Room
- [x] Repository Pattern
- [x] Observer Pattern
- [x] Retrofit2 (Gson, OkHttp3)
- [x] Dependency Injection
- [x] TypeConverters
- [x] SharedPreferences
- [x] Internationalization
- [x] Espresso Tests
- [x] Work Manager (implemented but not called)
- [x] Firebase (Crashlytics & Events)

Data is downloaded from [Covid19API](https://covid19api.com/)

### App Architecture
Covid-19 Worldwide Info uses MVVM architecture, observer & repository pattern to update the UI with the LiveData downloaded using Retrofit, also persisting data locally using Room.

<img src="/images/architecture.png" align="center" width="50%"/>

### License 
```
Copyright Covid-19 Worldwide Info by Pedro Rocha

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
