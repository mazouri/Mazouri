package com.mazouri.accounts;

import android.content.Context;

import com.mazouri.BuildConfig;
import com.mazouri.R;

import javax.inject.Inject;


public class AccountsManager extends BaseAccountsManager {
  @Inject
  public AccountsManager() {}
  @Override
  protected String[] getAccountTypes(Context context) {
    return new String[] { context.getString(R.string.account_type) };
  }

  @Override
  public boolean multipleAccountsAllowed() {
    return BuildConfig.DEBUG;
  }
}
