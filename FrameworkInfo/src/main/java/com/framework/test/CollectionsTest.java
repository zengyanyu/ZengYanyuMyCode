package com.framework.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.framework.domain.Role;

public class CollectionsTest {

	public static void main(String[] args) {
		List<Role> lstRole = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Role role = new Role();
			role.setName("角色" + i);
			role.setSn("角色编号" + i);
			role.setSrl(i);
			lstRole.add(role);
		}
		//升序
		test(lstRole, "ASC");
		for (Role role : lstRole) {
			System.out.println(role);
		}
	}

	public static void test(List<Role> lstRole, String desc) {
		java.util.Collections.sort(lstRole, new Comparator<Role>() {

			@Override
			public int compare(Role o1, Role o2) {
				if ("DESC".equals(desc)) {
					//降序
					return o2.getSrl().compareTo(o1.getSrl());
				} else if ("ASC".equals(desc)) {
					//升序
					return o1.getSrl().compareTo(o2.getSrl());
				} else {
					//升序
					return o1.getSrl().compareTo(o2.getSrl());
				}
			}
		});
	}

	public static void test(List<Role> lstRole) {
		test(lstRole, null);
	}

}
