package org.opensrp.service;

import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opensrp.api.domain.Location;
import org.opensrp.repository.AllLocations;
import org.opensrp.repository.HealthFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleFCMService {

	public void SendPushNotification(JSONObject msg, JSONArray to,boolean toFacilityUsers){
		try {
			String androidFcmKey;
			if(toFacilityUsers) {
				//Facility client app FCM server key
				androidFcmKey = "AAAAWBsBKRw:APA91bGhMedUBMMInWSeV4ahsFISP5bo_u3kQSOUA5Yc2opaQ8Y0MwmgjPuF7rRMuAiqTHGmrz2B00zvCaHdwfwxvcwJqpR05XoOnpyKy_el1gJsz8fgSikqzr8OgnV17z_2qZc6bbbp";
			}else{
				//Facility CHW app FCM server key
				androidFcmKey = "AAAACjP5eXU:APA91bGkhqul3WSJrAWP3og9Gadx2NtAfH759RMb9yFodhQ8BeSSVf1G9Zc8aUbM134JhVkWCXmAjucPF2h7a_7HYUjS8055FOlccnBNolZ-J3ImZoPNk7DnBAfQwzi2PGHLPe4f9EUc";
			}
			String androidFcmUrl="https://fcm.googleapis.com/fcm/send";

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Authorization", "key=" + androidFcmKey);
			httpHeaders.set("Content-Type", "application/json");

			JSONObject json = new JSONObject();

			json.put("data", msg);
			json.put("registration_ids", to);

			System.out.println("FCM Data:"+json.toString());

			HttpEntity<String> httpEntity = new HttpEntity<String>(json.toString(),httpHeaders);
			String response = restTemplate.postForObject(androidFcmUrl,httpEntity,String.class);
			System.out.println("FCM:"+response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
