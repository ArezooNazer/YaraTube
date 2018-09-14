package com.example.yaratech.yaratube.util;

import com.example.yaratech.yaratube.data.model.Category;
import com.example.yaratech.yaratube.data.model.Product;

public interface TransferToFragment {

    void goToMainContainerFragment();
    void goToProductGridFragment(Category category);
    void goToProductDetailFragment(Product product);
    void goToProfileFragment();
    void goToMainLoginDialogFragment();
    void goToCommentDialogFragment(int productId);
    void goToVideoPlayerActivity(String file);
    void goToAvatarOptionDialogFragment();
}
