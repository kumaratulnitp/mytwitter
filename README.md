********* Twitter **********

Requirement
1. User can register account - registerUser(username, name, email)
2. User can tweet - tweet(username, bodyText)
3. User can follow other users - follow(username, usernametofollow)
4. User can like a tweet - like(username, tweetId)
5. User can comment on a tweet - comment(username, tweetId, commentBodyText)
6. User should be able to see his tweet feed (which contains tweets from people he follows) - newsfeed(username)



Design
Basic objects we can see here which have their own life cycle and features
1. User - id, name, email, 
2. Tweet - id, text, userId, likes(of user ids), topLikes (userId and others), commentIds, topComment (userName, text)
3. Comment - id, tweetId, userId

What does a feed contain
When user requests for a feed-
List of people he follows is taken out
	Tweets from these people are fetched (posts done in last 1-2 days are fetched)
		Comments for these tweets are fetched (for obvious reasons, in a feed, we can have 1 comment shown)










