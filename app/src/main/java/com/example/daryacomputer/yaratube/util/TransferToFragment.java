package com.example.daryacomputer.yaratube.util;

import com.example.daryacomputer.yaratube.data.model.Category;
import com.example.daryacomputer.yaratube.data.model.File;
import com.example.daryacomputer.yaratube.data.model.Product;

public interface TransferToFragment {

    void goToMainContainerFragment();
    void goToProductGridFragment(Category category);
    void goToProductDetailFragment(Product product);
    void goToProfileFragment();
    void goToMainLoginDialogFragment();
    void goToCommentDialogFragment(int productId);
    void goToVideoPlayerActivity(String file);
}
