package com.company.oop.task.management.system.tests.utils.models.teamsmock;

import com.company.oop.task.management.system.models.teams.MemberImpl;
import com.company.oop.task.management.system.tests.utils.TestUtilities;

import static com.company.oop.task.management.system.models.teams.MemberImpl.MEMBER_NAME_MIN_LENGTH;

public class MemberMock {

    public static final String VALID_MEMBER_NAME = TestUtilities.getString(MEMBER_NAME_MIN_LENGTH + 1);
    public static final String INVALID_MEMBER_NAME = TestUtilities.getString(MEMBER_NAME_MIN_LENGTH - 1);

    public static MemberImpl getValidMockMember() {
        return new MemberImpl(VALID_MEMBER_NAME);
    }

}

