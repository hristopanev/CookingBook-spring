package net.cookingbook.service;

import net.cookingbook.service.models.services.PostServiceModel;
import net.cookingbook.service.models.services.RateServiceModel;

public interface RateService {

    void create(RateServiceModel rateServiceModel, PostServiceModel postServiceModel);

    RateServiceModel rate(String post_id, String user_id);

    boolean userHasRated(String rate_id, String user_id);

    RateServiceModel findRateByPostId(String id);
}
