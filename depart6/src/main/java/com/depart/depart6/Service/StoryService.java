package com.depart.depart6.Service;

import com.depart.depart6.Dto.StoryDto;
import com.depart.depart6.Entity.Story;
import com.depart.depart6.Entity.User;
import com.depart.depart6.Repository.StoryRepo;
import com.depart.depart6.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryService {
    private final StoryRepo storyRepository;


    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    public StoryService(StoryRepo storyRepository) {
        this.storyRepository = storyRepository;
    }

    public List<StoryDto> getStoriesSortedByProximity(double userLat, double userLon) {
        List<StoryDto> stories = storyRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(story -> calculateDistance(userLat, userLon, story.getLatitude(), story.getLongitude()))).map(story -> {


                    modelMapper.typeMap(Story.class, StoryDto.class).addMappings(mapper -> {
                                mapper.map(src -> src.getUserStory().getName(), StoryDto::setName);
                                mapper.map(src -> src.getUserStory().getImage(), StoryDto::setImage);

                            }


                    );
                    return modelMapper.map(story,StoryDto.class);
                })
                .collect(Collectors.toList());





        return stories;
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


    public String  saveStory(StoryDto storyDto,long id) {
        User user=userRepo.findById(id);
        Story story = new Story();
        story.setTime(storyDto.getTime());
        story.setOnline(storyDto.isOnline());
        story.setImages(storyDto.getImages());
        story.setLatitude(storyDto.getLatitude());
        story.setLongitude(storyDto.getLongitude());
        story.setUserStory(user);
        storyRepository.save(story);

        return new String("Kayıt başarılı");

    }
}
