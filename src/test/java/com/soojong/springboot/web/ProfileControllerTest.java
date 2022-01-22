package com.soojong.springboot.web;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfileControllerTest {

    @Test
    void real_profile이_조회된다() {
        //given
        String expectedprofile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedprofile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();
        assertThat(profile).isEqualTo(expectedprofile);
    }

    @Test
    public void real_profile이_없으면_첫_번째가_조회된다(){
        //given
        String expectedProfile ="oauth";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        //when
        String profile = controller.profile();
        assertThat(profile).isEqualTo(expectedProfile);
    }

}