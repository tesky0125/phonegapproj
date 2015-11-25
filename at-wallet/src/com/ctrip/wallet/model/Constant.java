package com.ctrip.wallet.model;

public interface Constant {
	interface ElemStatus {
		int Presence = 0;
		int Visible = 1;
		int Clickable = 2;
	}

	interface RealNameStatus {
		int None = 0;
		int Success = 1;
		int Fail = 2;
		int NoResult = 3;
		int Doing = 4;
	};
}
