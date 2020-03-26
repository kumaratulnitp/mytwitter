********* Twitter **********

Requirement
1. User can register account - registerUser(username, name, email)
2. User can tweet - tweet(username, bodyText)
3. User can follow other users - follow(username, usernametofollow)
4. User can like a tweet - like(username, tweetId)
5. User can comment on a tweet - comment(username, tweetId, commentBodyText)
6. User should be able to see his tweet feed (which contains tweets from people he follows) - newsfeed(username)

What kind of system are we trying to build 
1. It should be available
2. It should be partition tolerance (even if few things fails, system should be up)
3. Consistency is not of utmost importance. Eventual consistency will do just fine.


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


If we try to build a user's feed on his request, it would be a reactive approach and would require a lot of computation and joins to prepare his feed.
We can rather follow a proactive approach over here. We can have a newsfeed entity associated with every user. Once a user posts a tweet, a separate task would start up to add this tweet to each of his followers.

There is an inherent problem with this approach though. If a user has a million followers, every one of his tweet would trigger a task to update feed of his million followers. Imagine there being 100 such active popular twitter users. What should we do for this?
Although, when a person wants to see his newsfeed, showing tweets from his top rated users he follows would be necessary.

Let's get ahead with this approach for now in version 1.0.0








