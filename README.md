                                            Description
In that app I assume that all API calls are safe and properly typed by default. In real app safety can
be achieved through ORY or Spring Security roles and if the app is intended to be used by random people
with @Valid and @Validated annotations as well.

                                            How to start an app
This app is using Spring and React. To run it you need to run gradle and npm builds. After that start
Spring and React servers at the same time and open "http://localhost:3000/api/scoreBoard" in your browser.