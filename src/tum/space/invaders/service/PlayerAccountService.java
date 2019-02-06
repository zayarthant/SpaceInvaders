/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tum.space.invaders.service;

import tum.space.invaders.entity.PlayerAccount;

/**
 *
 * @author Zayar Thant
 */
public class PlayerAccountService {

	private static PlayerAccount playerAccount;

	public static PlayerAccount getPlayerAccount() {
		if(null == playerAccount)
			playerAccount = new PlayerAccount();
		return playerAccount;
	}

}
