package com.project.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.UserSerivceClinet;
import com.project.dto.Company;
import com.project.entity.CheckListCategory;
import com.project.entity.CheckListCategoryAndItems;
import com.project.entity.CheckListInfo;
import com.project.entity.CheckListItemsInfo;
import com.project.entity.CustomerRequirements;
import com.project.entity.ItemProcess;
import com.project.entity.ItemsImages;
import com.project.entity.KickOff;
import com.project.entity.KickOffItems;
import com.project.repository.*;

@RestController
@RequestMapping("/kickoff")
public class CompanyController {

    private final CheckListCategoryAndItemsRepository checkListCategoryAndItemsRepository;
	
	@Autowired
	private UserSerivceClinet userSerivceClinet;
	
	@Autowired
	private KickOffRepository kickOffRepository;
	
	@Autowired
	private KickOffItemsRepository kickOffItemsRepository;
	
	@Autowired
	private itemProcessRepository itemProcessRepository;
	
	@Autowired
	private ItemImageRepository itemImageRepository;
	
	@Autowired
	private CustomerRequirementsRepository customerRequirementsRepository;
	
	@Autowired
	private KickOffSignatureRepository kickOffSignatureRepository;
	
	@Autowired
	private CheckListInfoRepository checkListInfoRepository;
	
	@Autowired
	private CheckListItemsInfoRepository checkListItemsInfoRepository;
	
	@Autowired
	private CheckListCategoryRepository checkListCategoryRepository;
	
	Company company;

    CompanyController(CheckListCategoryAndItemsRepository checkListCategoryAndItemsRepository) {
        this.checkListCategoryAndItemsRepository = checkListCategoryAndItemsRepository;
    }

	@ModelAttribute
	public void companyDetails() {

		company = userSerivceClinet.getCompanyInfo();

	}
	
	@GetMapping
	public String  testing() {
		return "Testing";
	}
	
	@PostMapping("/createKickOffInfo")
	public ResponseEntity<?> createKickOffInfo(@RequestBody KickOff kickOff) {

		try {
			kickOff.setCreatedDateTime(LocalDateTime.now());
			kickOff.setCompanyId(company.getCompanyId());
			kickOffRepository.save(kickOff);
			return ResponseEntity.ok(kickOff);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PostMapping("/saveKickOffItems")
	public ResponseEntity<?> saveKickOffItems(@RequestBody List<KickOffItems> items) {

		try {
			
			List<KickOffItems> savedItems = kickOffItemsRepository.saveAll(items);
			
			
			 for (KickOffItems savedItem : savedItems) {
		            if (savedItem.getImageList() != null && !savedItem.getImageList().isEmpty()) {
		                for (String base64Image : savedItem.getImageList()) {
		                   ItemsImages  image = new ItemsImages();
		                    image.setItemId(savedItem.getItemId());
		                    image.setImage(Base64.getDecoder().decode(base64Image));
		                    itemImageRepository.save(image);
		                }
		            }
		        }
			
			return ResponseEntity.ok(items);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PostMapping("/saveKickOffItemsProccess")
	public ResponseEntity<?> saveKickOffItemsProccess(@RequestBody List<ItemProcess> items) {

		try {
			
			List<ItemProcess> saved = itemProcessRepository.saveAll(items);
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PostMapping("/saveCustomerRequirements")
	public ResponseEntity<?> saveCustomerRequirements(@RequestBody List<CustomerRequirements> customerRequirements) {

		try {
			
			List<CustomerRequirements> saved = customerRequirementsRepository.saveAll(customerRequirements);
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@PostMapping("/saveKickOffSignature")
	public ResponseEntity<?> saveKickOffSignature(@RequestBody List<KickOffSignature> kickOffSignature) {

		try {
			
			List<KickOffSignature> saved = kickOffSignatureRepository.saveAll(kickOffSignature);
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	@GetMapping("/getKickOffInfo/{kickOffId}")
	public ResponseEntity<?> getKickOffInfo(@PathVariable String kickOffId) {

		try {

			KickOff kickOff = kickOffRepository.findBykickOffId(kickOffId);
			List<KickOffItems> kickOffItemsList = kickOffItemsRepository.findByKickOffId(kickOffId);
			List<ItemProcess> itemProcessList = itemProcessRepository.findByKickOffId(kickOffId);
			List<CustomerRequirements> requirementList = customerRequirementsRepository.findByKickOffId(kickOffId);
			List<KickOffSignature> listofSingnature = kickOffSignatureRepository.findByKickOffId(kickOffId);
			
			List<String> itemIds = kickOffItemsList.stream()
			        .map(KickOffItems::getItemId)
			        .collect(Collectors.toList());

			// Step 2: Fetch all images with itemId IN (...)
			List<ItemsImages> allImages = itemImageRepository.findByItemIdIn(itemIds);
			
			// Step 3: Group images by itemId
			Map<String, List<String>> imagesByItemId = allImages.stream()
			        .collect(Collectors.groupingBy(
			            ItemsImages::getItemId,
			            Collectors.mapping(img -> Base64.getEncoder().encodeToString(img.getImage()), Collectors.toList())
			        ));

			// Step 4: Set imageList into KickOffItems
			for (KickOffItems item : kickOffItemsList) {
			    List<String> imageList = imagesByItemId.getOrDefault(item.getItemId(), new ArrayList<>());
			    item.setImageList(imageList);
			}

			Map<String, Object> data = new HashMap<>();
			data.put("kickOffInfo", kickOff);
			data.put("kickOffItemsList", kickOffItemsList);
			data.put("itemProcessList", itemProcessList);
			data.put("requirementList", requirementList);
			data.put("listofSingnature", listofSingnature);
			return ResponseEntity.ok(data);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	@PutMapping("/updateKickOffInfo")
	public ResponseEntity<?> updateKickOffInfo(@RequestBody KickOff kickOff) {

		try {
			kickOff.setCompanyId(company.getCompanyId());
			kickOffRepository.save(kickOff);
			return ResponseEntity.ok(kickOff);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	@PutMapping("/updateItem")
	public ResponseEntity<?> updateItem(@RequestBody KickOffItems kickOffItem) {

		try {
			kickOffItemsRepository.save(kickOffItem);
			return ResponseEntity.ok(kickOffItem);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@DeleteMapping("/deleteItem/{itemId}")
	public ResponseEntity<?> deleteItem(@PathVariable String itemId) {

		try {
		
			kickOffItemsRepository.deleteById(itemId);
			return ResponseEntity.ok("Item Deleted ");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	
	@PostMapping(value="/addItemImage", consumes = {"multipart/form-data"})
	public ResponseEntity<?> addItemImage(@RequestPart(value = "image", required = false) MultipartFile image ,
			@RequestPart("itemId") String itemId) {

		try {
			ItemsImages itemImage=new ItemsImages();
			itemImage.setImage(image.getBytes());
			itemImage.setItemId(itemId);
			itemImageRepository.save(itemImage);
			return ResponseEntity.ok(itemImage);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@DeleteMapping("/deleteItemImage/{imageId}")
	public ResponseEntity<?> deleteItemImage(@PathVariable String imageId) {

		try {
		
			itemImageRepository.deleteById(imageId);
			return ResponseEntity.ok("Image Deleted ");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@PutMapping("/updateKickOffItemsProccess")
	public ResponseEntity<?> updateKickOffItemsProccess(@RequestBody List<ItemProcess> items) {

		try {
			
			List<ItemProcess> saved = itemProcessRepository.saveAll(items);
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@DeleteMapping("/deleteItemProcess/{processId}")
	public ResponseEntity<?> deleteItemProcess(@PathVariable String processId) {

		try {
		
			itemProcessRepository.deleteById(processId);
			return ResponseEntity.ok("Process Deleted ");

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PutMapping("/updateCustomerRequirements")
	public ResponseEntity<?> updateCustomerRequirements(@RequestBody List<CustomerRequirements> customerRequirements) {

		try {
			
			List<CustomerRequirements> saved = customerRequirementsRepository.saveAll(customerRequirements);
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PutMapping("/updateKickOffSignature")
	public ResponseEntity<?> updateKickOffSignature(@RequestBody List<KickOffSignature> kickOffSignature) {

		try {
			
			List<KickOffSignature> saved = kickOffSignatureRepository.saveAll(kickOffSignature);
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@GetMapping("/getAllKickOffs/{page}/{size}")
	public ResponseEntity<?> getAllKickOffs(@PathVariable int page,@PathVariable int size, @RequestParam(defaultValue = "") String projectName) {
		try {
			
			
			Map<String ,Object> data=new HashMap<String , Object>();
			 Pageable pageable = PageRequest.of(page, size, Sort.by("createdDateTime").descending());
			
		        Page<KickOff> kickOffPage = kickOffRepository.findByCompanyIdAndProjectNameContainingIgnoreCase(company.getCompanyId(),projectName, pageable);
		        List<KickOff> kickOffList = kickOffPage.getContent();
		        data.put("kickOffList", kickOffList);
		        data.put("totalPages", kickOffPage.getTotalPages());
		        data.put("currentPage", kickOffPage.getNumber());
			return ResponseEntity.ok(data);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	@PostMapping("/createCheckList")
	public ResponseEntity<?> createCheckList(@RequestBody Map<String, Object> request) {
	    try {
	        // Step 1: Extract "checkListInfo" map and convert to CheckList object
	        ObjectMapper mapper = new ObjectMapper();
	        
	        // Convert to CheckList
	        CheckListInfo checkList = mapper.convertValue(request.get("checkListInfo"), CheckListInfo.class);

	        // Save the checklist
	        CheckListInfo savedCheckList = checkListInfoRepository.save(checkList);
	        String checkListId = savedCheckList.getCheckListId();

	        // Step 2: Extract "checkListItems" list and convert to List<CheckListItem>
	        List<Map<String, Object>> itemsList = (List<Map<String, Object>>) request.get("checkListItems");

	        List<CheckListItemsInfo> itemsToSave = new ArrayList<>();
	        for (Map<String, Object> itemMap : itemsList) {
	        	CheckListItemsInfo item = mapper.convertValue(itemMap, CheckListItemsInfo.class);
	            item.setCheckListId(checkListId); // Set foreign key
	            itemsToSave.add(item);
	        }

	        checkListItemsInfoRepository.saveAll(itemsToSave);

	        return ResponseEntity.ok("Checklist and items created successfully.");

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error: " + e.getMessage());
	    }
	}
	
	
	@PutMapping("/updateCheckList")
	public ResponseEntity<?> updateCheckList(@RequestBody Map<String, Object> request) {
	    try {
	        // Step 1: Extract "checkListInfo" map and convert to CheckList object
	        ObjectMapper mapper = new ObjectMapper();
	        
	        // Convert to CheckList
	        CheckListInfo checkList = mapper.convertValue(request.get("checkListInfo"), CheckListInfo.class);

	        // Save the checklist
	        checkList.setCreatedDateTime(LocalDateTime.now());
	        checkList.setCompanyId(company.getCompanyId());
	        CheckListInfo savedCheckList = checkListInfoRepository.save(checkList);
	        String checkListId = savedCheckList.getCheckListId();

	        // Step 2: Extract "checkListItems" list and convert to List<CheckListItem>
	        List<Map<String, Object>> itemsList = (List<Map<String, Object>>) request.get("checkListItems");

	        List<CheckListItemsInfo> itemsToSave = new ArrayList<>();
	        for (Map<String, Object> itemMap : itemsList) {
	        	CheckListItemsInfo item = mapper.convertValue(itemMap, CheckListItemsInfo.class);
	            itemsToSave.add(item);
	        }

	        checkListItemsInfoRepository.saveAll(itemsToSave);

	        return ResponseEntity.ok("Checklist and items created successfully.");

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error: " + e.getMessage());
	    }
	}
	
	
	@GetMapping("/getCheckListData/{checkListId}")
	public ResponseEntity<?> getCheckListData(@PathVariable String checkListId) {

		try {
			
			Map<String,Object> data=new HashMap<>();
			
			CheckListInfo checkListInfo=checkListInfoRepository.findByCheckListId(checkListId);
			List<CheckListItemsInfo> checkListItemsInfo=checkListItemsInfoRepository.findByCheckListId(checkListId);
			
			data.put("checkListInfo", checkListInfo);
			data.put("checkListItemsList",checkListItemsInfo);
			
			return ResponseEntity.ok(data);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	
	@GetMapping("/getAllCheckList/{page}/{size}")
	public ResponseEntity<?> getAllCheckList(@PathVariable int page,@PathVariable int size, @RequestParam(defaultValue = "") String projectName) {
		try {
			
			
			Map<String ,Object> data=new HashMap<String , Object>();
			 Pageable pageable = PageRequest.of(page, size, Sort.by("createdDateTime").descending());
			
		        Page<CheckListInfo> checkListInfoPage = checkListInfoRepository.findByCompanyIdAndProjectNameContainingIgnoreCase(company.getCompanyId(),projectName, pageable);
		        List<CheckListInfo> allCheckList = checkListInfoPage.getContent();
		        data.put("allCheckList", allCheckList);
		        data.put("totalPages", checkListInfoPage.getTotalPages());
		        data.put("currentPage", checkListInfoPage.getNumber());
			return ResponseEntity.ok(data);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PostMapping("/createCheckListCategory")
	public ResponseEntity<?> createCheckListCategory(@RequestBody CheckListCategory checkListCategory) {

		try {
			checkListCategory.setCompanyId(company.getCompanyId());
			
			return ResponseEntity.ok(checkListCategoryRepository.save(checkListCategory));

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PutMapping("/updateCheckListCategory")
	public ResponseEntity<?> updateCheckListCategory(@RequestBody List<CheckListCategory> checkListCategory) {

		try {
			List<CheckListCategory>  saved=checkListCategoryRepository.saveAll(checkListCategory);
			
			return ResponseEntity.ok(saved);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@GetMapping("/getCheckListCategory")
	public ResponseEntity<?> getCheckListCategory() {

		try {
			List<CheckListCategory>  categoryList=checkListCategoryRepository.findByCompanyIdOrderBySequence(company.getCompanyId());
			
			return ResponseEntity.ok(categoryList);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	@PostMapping("/createCheckListCategoryWithItem")
	public ResponseEntity<?> createCheckListCategoryWithItem(@RequestBody CheckListCategoryAndItems checkListCategoryAndItems) {

		try {
			checkListCategoryAndItems.setCompanyId(company.getCompanyId());
			
			return ResponseEntity.ok(checkListCategoryAndItemsRepository.save(checkListCategoryAndItems));

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@PutMapping("/updateCheckListCategoryWithItem")
	public ResponseEntity<?> updateCheckListCategoryWithItem(@RequestBody List<CheckListCategoryAndItems> checkListCategoryAndItems) {

		try {
			
			
			return ResponseEntity.ok(checkListCategoryAndItemsRepository.saveAll(checkListCategoryAndItems));

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}
	
	
	@GetMapping("/getCheckListCategoryAndItem")
	public ResponseEntity<?> getCheckListCategoryAndItem() {

		try {
			List<CheckListCategoryAndItems>  itemListList=checkListCategoryAndItemsRepository.findByCompanyIdOrderBySequence(company.getCompanyId());
			
			return ResponseEntity.ok(itemListList);

		} catch (Exception e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
		}
	}

}
