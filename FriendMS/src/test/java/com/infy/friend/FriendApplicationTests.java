package com.infy.friend;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.friend.dto.FriendDTO;
import com.infy.friend.entity.Friend;
import com.infy.friend.exception.FriendException;
import com.infy.friend.repository.FriendRepository;
import com.infy.friend.service.FriendService;
import com.infy.friend.service.FriendServiceImpl;

@SpringBootTest
class FriendApplicationTests {

	@Mock
	private FriendRepository friendRepository;

	@InjectMocks
	private FriendService friendService = new FriendServiceImpl();

	@Test
	public void createFriendshipValidTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);

		Mockito.when(friendRepository.findByUserIdAndFriendId(userId, friendId)).thenReturn(Optional.empty());
		Mockito.when(friendRepository.save(Mockito.any(Friend.class))).thenReturn(friend);
		boolean flag = friendService.createFriendship(userId, friendId);

		Assertions.assertEquals(true, flag);
		Assertions.assertEquals("friendship111", friend.getFriendshipId());
	}

	@Test
	public void createFriendshipInvalidTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);

		Mockito.when(friendRepository.findByUserIdAndFriendId(userId, friendId)).thenReturn(Optional.of(friend));
		Mockito.when(friendRepository.save(Mockito.any(Friend.class))).thenReturn(friend);

		FriendException actual = Assertions.assertThrows(FriendException.class,
				() -> friendService.createFriendship(userId, friendId));
		Assertions.assertEquals("FRIENDSHIP_EXISTS", actual.getMessage());
	}

	@Test
	public void editCategoryValidTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);

		Mockito.when(friendRepository.findByUserIdAndFriendId(userId, friendId)).thenReturn(Optional.of(friend));
		boolean flag = friendService.editCategory(userId, friendId, "");

		Assertions.assertEquals(true, flag);
	}

	@Test
	public void editCategoryInvalidTest() throws FriendException {
		String userId2 = "user222";
		String friendId2 = "friend222";

		Mockito.when(friendRepository.findByUserIdAndFriendId(userId2, friendId2)).thenReturn(Optional.empty());
		FriendException actual = Assertions.assertThrows(FriendException.class,
				() -> friendService.editCategory(userId2, friendId2, ""));
		Assertions.assertEquals("NO_FRIENDSHIP", actual.getMessage());
	}

	@Test
	public void unFollowInvalidTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);

		Mockito.when(friendRepository.findByUserIdAndFriendId(userId, friendId)).thenReturn(Optional.empty());
		FriendException actual = Assertions.assertThrows(FriendException.class, () -> friendService.unFollow(userId, friendId));
		Assertions.assertEquals("NO_FRIENDSHIP", actual.getMessage());
	}

	@Test
	public void unFollowValidTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);

		Mockito.when(friendRepository.findByUserIdAndFriendId(userId, friendId)).thenReturn(Optional.of(friend));
		boolean flag = friendService.unFollow(userId, friendId);
		Assertions.assertEquals(true, flag);
	}

	@Test
	public void checkFriendshipFalseTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);

		Mockito.when(friendRepository.findByUserIdAndFriendId(userId, friendId)).thenReturn(Optional.empty());
		boolean flag = friendService.checkFriendship(userId, friendId);
		Assertions.assertEquals(false, flag);
	}

	@Test
	public void checkFriendshipTrueTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);

		Mockito.when(friendRepository.findByUserIdAndFriendId(userId, friendId)).thenReturn(Optional.of(friend));
		boolean flag = friendService.checkFriendship(userId, friendId);
		Assertions.assertEquals(true, flag);
	}

	@Test
	public void friendsByIdInvalidTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);
		List<Friend> list = List.of();

		Mockito.when(friendRepository.findByUserId(userId)).thenReturn(list);
		FriendException actual = Assertions.assertThrows(FriendException.class, () -> friendService.friendsById(userId));
		Assertions.assertEquals("NO_FRIENDSHIPS", actual.getMessage());
	}

	@Test
	public void friendsByIdValidTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);
		List<Friend> list = List.of(friend);
		List<FriendDTO> list2 = List.of(FriendDTO.fromEntity(friend));

		Mockito.when(friendRepository.findByUserId(userId)).thenReturn(list);
		List<FriendDTO> actual = friendService.friendsById(userId);
		Assertions.assertEquals(list2.get(0).getFriendshipId(), actual.get(0).getFriendshipId());
	}

	@Test
	public void countFriendsTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);
		List<Friend> list = List.of(friend);

		Mockito.when(friendRepository.findByUserId(userId)).thenReturn(list);
		Integer cnt = friendService.friendsCount(userId);
		Assertions.assertEquals(1, cnt);
	}

	@Test
	public void friendsByIdCategoryInvalidTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);
		List<Friend> list = List.of();

		Mockito.when(friendRepository.findByUserIdAndCategory(userId, "c")).thenReturn(list);
		FriendException actual = Assertions.assertThrows(FriendException.class, () -> friendService.friendsByCategory(userId, "c"));
		Assertions.assertEquals("NO_FRIENDSHIPS", actual.getMessage());
	}

	@Test
	public void friendsByIdcCtegoryValidTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);
		List<Friend> list = List.of(friend);
		List<FriendDTO> list2 = List.of(FriendDTO.fromEntity(friend));

		Mockito.when(friendRepository.findByUserIdAndCategory(userId, "c")).thenReturn(list);
		List<FriendDTO> actual = friendService.friendsByCategory(userId, "c");
		Assertions.assertEquals(list2.get(0).getFriendshipId(), actual.get(0).getFriendshipId());
	}

	@Test
	public void categoriesByUserTest() throws FriendException {
		String userId = "user111";
		String friendId = "friend111";

		Friend friend = new Friend();
		friend.setFriendshipId("friendship111");
		friend.setUserId(userId);
		friend.setFriendId(friendId);
		friend.setBlocked(false);
		friend.setUnfollowed(false);
		friend.setCategory("c");

		List<Friend> list = List.of(friend);
		Set<String> categories = Set.of("c");

		Mockito.when(friendRepository.findByUserId("user222")).thenReturn(List.of());
		Assertions.assertThrows(FriendException.class, () -> friendService.categoriesByUser("user222"));

		Mockito.when(friendRepository.findByUserId(userId)).thenReturn(list);
		Set<String> actual = friendService.categoriesByUser(userId);
		Assertions.assertEquals(categories.size(), actual.size());
	}
}
