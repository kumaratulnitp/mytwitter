#register
curl --location --request PUT 'localhost:8080/user?userName=u1&name=user%201&email=ds@gmail.com'
curl --location --request PUT 'localhost:8080/user?userName=u2&name=user%202&email=ds@gmail.com'
curl --location --request PUT 'localhost:8080/user?userName=u3&name=user%203&email=ds@gmail.com'
curl --location --request PUT 'localhost:8080/user?userName=u4&name=user%204&email=ds@gmail.com'
curl --location --request PUT 'localhost:8080/user?userName=u5&name=user%205&email=ds@gmail.com'

#follow
curl --location --request GET 'localhost:8080/follow?userNameToFollow=u2&followerUserName=u1'
curl --location --request GET 'localhost:8080/follow?userNameToFollow=u3&followerUserName=u1'
curl --location --request GET 'localhost:8080/follow?userNameToFollow=u1&followerUserName=u2'
curl --location --request GET 'localhost:8080/follow?userNameToFollow=u3&followerUserName=u2'
curl --location --request GET 'localhost:8080/follow?userNameToFollow=u4&followerUserName=u2'
curl --location --request GET 'localhost:8080/follow?userNameToFollow=u5&followerUserName=u2'
curl --location --request GET 'localhost:8080/follow?userNameToFollow=u1&followerUserName=u3'
curl --location --request GET 'localhost:8080/follow?userNameToFollow=u2&followerUserName=u4'


#tweet
curl --location --request PUT 'localhost:8080/tweet?text=my%20tweet%20is%20working&userName=u1'
curl --location --request PUT 'localhost:8080/tweet?text=my%20tweet%20is%20nice&userName=u3'
curl --location --request PUT 'localhost:8080/tweet?text=thisismeh&userName=u2'
curl --location --request PUT 'localhost:8080/tweet?text=goodattempt&userName=u4'
curl --location --request PUT 'localhost:8080/tweet?text=MyFirstWin&userName=u5'
curl --location --request PUT 'localhost:8080/tweet?text=HelloWorld&userName=u1'
curl --location --request PUT 'localhost:8080/tweet?text=my%20tweet%20is%20not%20working&userName=u2'
curl --location --request PUT 'localhost:8080/tweet?text=FOLLOWME&userName=u3'
curl --location --request PUT 'localhost:8080/tweet?text=IAMTIRED&userName=u1'


#comment
curl --location --request PUT 'localhost:8080/comment?userName=u1&tweetId=1&text=wow'
curl --location --request PUT 'localhost:8080/comment?userName=u2&tweetId=1&text=super'
curl --location --request PUT 'localhost:8080/comment?userName=u1&tweetId=1&text=amazing'
curl --location --request PUT 'localhost:8080/comment?userName=u2&tweetId=2&text=nice'
curl --location --request PUT 'localhost:8080/comment?userName=u4&tweetId=2&text=good'
curl --location --request PUT 'localhost:8080/comment?userName=u5&tweetId=3&text=perfect'
curl --location --request PUT 'localhost:8080/comment?userName=u2&tweetId=3&text=class'
curl --location --request PUT 'localhost:8080/comment?userName=u3&tweetId=4&text=jabar'
curl --location --request PUT 'localhost:8080/comment?userName=u4&tweetId=1&text=gazab'
curl --location --request PUT 'localhost:8080/comment?userName=u5&tweetId=3&text=superseuper'
curl --location --request PUT 'localhost:8080/comment?userName=u1&tweetId=4&text=mahan'
curl --location --request PUT 'localhost:8080/comment?userName=u2&tweetId=2&text=bakwas'


#like
curl --location --request PUT 'localhost:8080/like?tweetId=0&userName=u2'
curl --location --request PUT 'localhost:8080/like?tweetId=1&userName=u3'
curl --location --request PUT 'localhost:8080/like?tweetId=1&userName=u4'
curl --location --request PUT 'localhost:8080/like?tweetId=1&userName=u5'
curl --location --request PUT 'localhost:8080/like?tweetId=2&userName=u1'
curl --location --request PUT 'localhost:8080/like?tweetId=3&userName=u2'
curl --location --request PUT 'localhost:8080/like?tweetId=4&userName=u3'

