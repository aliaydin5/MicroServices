package com.depart.depart6.Service;

import com.depart.depart6.Entity.Story;
import com.depart.depart6.Repository.StoryRepo;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryService {
    private final StoryRepo storyRepository;

    public StoryService(StoryRepo storyRepository) {
        this.storyRepository = storyRepository;
    }

    public List<Story> getStoriesSortedByProximity(double userLat, double userLon) {
        List<Story> stories = storyRepository.findAll();

        return stories.stream()
                .sorted(Comparator.comparingDouble(story -> calculateDistance(userLat, userLon, story.getLatitude(), story.getLongitude())))
                .collect(Collectors.toList());
    }

    // Haversine formülü ile iki nokta arasındaki mesafeyi hesaplama (kilometre cinsinden)
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Dünya yarıçapı (km)

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}
