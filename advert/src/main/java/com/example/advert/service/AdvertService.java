package com.example.advert.service;



import com.example.advert.client.PurchaseCountClient;
import com.example.advert.client.UserClient;
import com.example.advert.dto.AdvertDTO;
import com.example.advert.dto.PurchaseCountDTO;
import com.example.advert.enums.AdvertStatus;
import com.example.advert.enums.BuildType;
import com.example.advert.enums.Currency;
import com.example.advert.enums.PublicationType;
import com.example.advert.exception.GenericServiceException;
import com.example.advert.mapper.AdvertMapper;
import com.example.advert.model.Address;
import com.example.advert.model.Advert;
import com.example.advert.repository.AdvertRepository;
import com.example.advert.util.DateFormatUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.ZonedDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvertService {
    private final AdvertMapper advertMapper;
    private final AdvertRepository advertRepository;
    private final UserClient userClient;
    private final PurchaseCountClient purchaseCountClient;
    private final QueueService queueService;

    public List<AdvertDTO> getAllList() {
        return advertMapper.toDTOList(advertRepository.findAll());
    }

    @Transactional
    public AdvertDTO create(AdvertDTO advertDTO,UUID id) {
        advertDTO.setAdvertNo((long) (1 + new Random().nextInt(900000000)));
        userClient.getUserById(id);
        checkPurchaseCount(id);
        advertDTO.setAdvertStatus(AdvertStatus.IN_REVIEW);
        advertDTO.setUserId(id);
        Advert advert = advertRepository.save(advertMapper.toEntity(advertDTO));
        writeQueue(advert.getId());
        return advertMapper.toDTO(advert);
    }

    @Transactional
    public AdvertDTO update(AdvertDTO advertDTO) {
        getById(advertDTO.getId());
        checkStatuControl(advertDTO.getAdvertStatus());
        return advertMapper.toDTO(advertRepository.save(advertMapper.toEntity(advertDTO)));
    }

    public void checkStatuControl(AdvertStatus advertStatus){
        if(advertStatus.equals(AdvertStatus.IN_REVIEW)){
            throw new GenericServiceException(GenericServiceException.OPERATION_NOT_ALLOWED,"Your status is not available for update");
        }
    }

    public AdvertDTO getAdvertByAdvertNo(Long advertNo){
        return advertMapper.toDTO(advertRepository.findByAdvertNo(advertNo).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"Advert not found")));
    }

    public AdvertDTO getById(Long id){
        return advertMapper.toDTO(advertRepository.findById(id).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"Advert not found")));
    }

    public void checkPurchaseCount(UUID id){
        ResponseEntity<PurchaseCountDTO> purchaseCount = purchaseCountClient.getPurchaseCount(id);
        if(purchaseCount.getBody() != null && purchaseCount.getBody().getRemainingTotal() > 0){
            purchaseCount.getBody().setRemainingTotal(purchaseCount.getBody().getRemainingTotal() - 1);
            purchaseCountClient.update(purchaseCount.getBody());
        }else{
            throw new GenericServiceException(GenericServiceException.CANT_CREATE_ADVERT,"You don't have the right to create advert.You must purchase package");
        }
    }

    public void writeQueue(Long id){
        queueService.changeStatus(id);
    }

    public Page<AdvertDTO> getAllListWithFilter(Pageable pageable, ZonedDateTime creationDate,
                                                Long advertNo, Currency currency, Double price,
                                                Double netSquareMeters, Double squareMeters, Integer room,
                                                Integer livingRoom, Integer age, Integer bathRoom, Integer numberOfFloor,
                                                String floor, PublicationType publicationType, BuildType buildType, Boolean active,
                                                String roomAndLivingRoom, String province, String district) {
        Page<Advert> adverts = advertRepository.findAll((Specification<Advert>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (floor != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("floor")), "%" + floor.toLowerCase(new Locale("tr", "TR")) + "%")));
            }
            if (roomAndLivingRoom != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("roomAndLivingRoom")), "%" + roomAndLivingRoom.toLowerCase(new Locale("tr", "TR")) + "%")));
            }
            if (advertNo != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("advertNo"), advertNo)));
            }
            if (currency != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("currency"), currency)));
            }
            if (publicationType != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("publicationType"), publicationType)));
            }
            if (buildType != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("buildType"), buildType)));
            }
            if (price != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("price"), price)));
            }
            if (netSquareMeters != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("netSquareMeters"), netSquareMeters)));
            }
            if (squareMeters != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("squareMeters"), squareMeters)));
            }
            if (room != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("room"), room)));
            }
            if (livingRoom != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("livingRoom"), livingRoom)));
            }
            if (age != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("age"), age)));
            }
            if (bathRoom != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("bathRoom"), bathRoom)));
            }
            if (numberOfFloor != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("numberOfFloor"), numberOfFloor)));
            }
            if (province != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("address").get("province")), "%" + province.toLowerCase(new Locale("tr", "TR")) + "%")));
            }
            if (district != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("address").get("district")), "%" + district.toLowerCase(new Locale("tr", "TR")) + "%")));
            }
            if(creationDate != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.between(root.get("creationDate"), DateFormatUtils.addFromLocalTime(creationDate), DateFormatUtils.addToLocalTime(creationDate))));
            }
            if(active != null){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("active"), active)));
            }else{
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("active"), true)));
            }
            query.orderBy(criteriaBuilder.desc(root.get("modificationDate")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        List<AdvertDTO> advertDTOList = advertMapper.toDTOList(adverts.getContent());
        return new PageImpl<>(advertDTOList, pageable, adverts.getTotalElements());
    }
}
