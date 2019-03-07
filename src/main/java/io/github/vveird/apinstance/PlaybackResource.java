package io.github.vveird.apinstance;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("playbackResource")
public class PlaybackResource {

	static CopyOnWriteArrayList<PlaybackLineDescriptor> lineDescriptors = new CopyOnWriteArrayList<>();

	static {
		CopyOnWriteArrayList<String> playback = new CopyOnWriteArrayList<String>();
		playback.add("Song A");
		playback.add("Song B");
		playback.add("Song C");
		playback.add("Song D");
		PlaybackLineDescriptor pll = new PlaybackLineDescriptor.PlaybackLineDescriptorBuilder().id().name("default")
				.type("ambience").loop(true).build();
		pll.setQueue(playback);
		lineDescriptors.add(pll);
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlaybackLineDescriptor> listAll() {
		return lineDescriptors;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PlaybackLineDescriptor getCustomer(@PathParam("id") long id) {
		Optional<PlaybackLineDescriptor> match = lineDescriptors.stream().filter(c -> c.getId() == id).findFirst();
		if (match.isPresent()) {
			return match.get();
		} else {
			return new PlaybackLineDescriptor();
		}
	}

	@POST
	@Path("/add")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public PlaybackLineDescriptor addLine(PlaybackLineDescriptor pld) {
		PlaybackLineDescriptor pldAdd = new PlaybackLineDescriptor.PlaybackLineDescriptorBuilder().id()
				.name(pld.getName()).loop(pld.isLoop()).type(pld.getType()).queue(pld.getQueue()).build();
		lineDescriptors.add(pldAdd);
		return pldAdd;

	}

}
